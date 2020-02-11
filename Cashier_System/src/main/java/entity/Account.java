package entity;

import lombok.Data;

/**
 * Description:lombok插件--提供set、get方法；
 *
 * @author: KangWuBin
 * @Date: 2020/2/9
 * @Time: 16:51
 */
@Data
public class Account {
    private Integer id;
    private String username;
    private String password;
}
