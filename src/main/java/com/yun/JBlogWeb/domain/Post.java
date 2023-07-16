package com.yun.JBlogWeb.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 100)
	private String title;

	// Summernote를 적용하면 다양한 <html> 태그가 포함되기 때문에 Large Object
	@Lob
	@Column(nullable = false)
	private String content;

	@CreationTimestamp
	private Timestamp createDate;

	private int cnt;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userid") // post 테이블의 userid 컬럼(외래키로 사용)으로 조인
	private User user;

	@OneToMany(mappedBy = "post", fetch = FetchType.EAGER) // mappedBy 속성 사용 => 연관 관계의 주인이 아님
	@OrderBy("id desc")
	private List<Reply> replyList;

}
