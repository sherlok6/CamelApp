package org.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Suggestion {
    private String value;
    private String unrestricted_value;

    private Data data;
}
