package com.yun.JBlogWeb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

	@NotNull(message = "Title이 전달되지 않았습니다.")
	@NotBlank(message = "제목은 필수 입력 항목입니다.")
	private String title;

	@NotNull(message = "Content가 전달되지 않았습니다.")
	@NotBlank(message = "내용은 필수 입력 항목입니다.")
	private String content;

}
