package com.develop.jina1.adminPanel.category;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryFilterCommand {
    private Long id;
    private String title;
}
