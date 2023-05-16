package com.hniesep.framework.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author HKRR
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentListVO<T> implements Serializable {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long total;
    private T rows;
}
