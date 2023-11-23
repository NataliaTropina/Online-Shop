package com.example.demo.todo.dto;

import com.example.demo.todo.models.CartDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewCartDto {

    private List<CartDetails> cartDetails;

}
