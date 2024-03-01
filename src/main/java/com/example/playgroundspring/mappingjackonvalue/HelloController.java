package com.example.playgroundspring.mappingjackonvalue;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.UUID;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public ResponseEntity<MappingJacksonValue> getHello() {
        Hello hello = Hello.builder()
            .id(UUID.randomUUID().toString())
            .msg("hello")
            .timeStamp(LocalDate.now())
            .build();

        return ResponseEntity.ok(wrapResponseWithCustomFilter(hello));
    }

    private MappingJacksonValue wrapResponseWithCustomFilter(Object response) {
        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.serializeAllExcept("id");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("helloFilter", simpleBeanPropertyFilter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(response);
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }

    @JsonFilter("helloFilter")
    @Getter
    static class Hello {
        private String id;
        private String msg;
        private LocalDate timeStamp;

        @Builder
        public Hello(String id, String msg, LocalDate timeStamp) {
            this.id = id;
            this.msg = msg;
            this.timeStamp = timeStamp;
        }
    }
}


