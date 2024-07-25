package com.Broadcomapp.message.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Template {
    private String templateName;
    private Map<String,Object> variable;
    private Long groupId;
}
