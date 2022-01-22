package org.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DaDataRequest {
    private String query;
    private Integer count;

    private Locations[] locations;
}
