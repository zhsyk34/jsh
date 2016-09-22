package com.cat.jsh.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role {

	private Long roleId;

	private String name;

	private LocalDateTime createTime;

	private LocalDateTime updateTime;
}
