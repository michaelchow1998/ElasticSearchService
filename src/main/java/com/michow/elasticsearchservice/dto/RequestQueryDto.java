package com.michow.elasticsearchservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestQueryDto {

    private String index;
    private String field;
    private String keyword;
}
