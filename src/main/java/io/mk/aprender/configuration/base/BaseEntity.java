package io.mk.aprender.configuration.base;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@ToString
@EqualsAndHashCode
@Builder
public class BaseEntity implements Serializable {

    public BaseEntity(){

    }

}
