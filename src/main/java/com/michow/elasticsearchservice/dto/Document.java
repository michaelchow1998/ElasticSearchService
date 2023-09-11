package com.michow.elasticsearchservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Document {
    private String id;
    private String index;
    private String type;
    private String message;
}
