package org.simpleframework.aop.aspect;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: TODO
 * @author: zjh
 * @date: 2022年05月22日 16:47
 */
@AllArgsConstructor
@Getter
public class AspectInfo {
    private int orderIndex;
    private DefaultAspect aspectObject;
}
