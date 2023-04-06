package hello.itemservice.domain.item;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
//@ScriptAssert(lang="javascript",script = "_this.price * _this.quantity >= 10000")  // 오브젝트 오류 확인. 되도록이면 이런 오브젝트 오류는 직접 자바코드로 하는것이 낫다.
public class Item {


    //groups는 실무에서 잘 사용하지 않음. 현재 지마켓 프로젝트처럼 컨트롤러가 받아주는 dto와 실제 저장을 하는 cmd처럼 객체분리를 통해 처리한다.
    //groups는 @Valid 자바 표준에서는 사용하지 못한다.
    @NotNull(groups = UpdateCheck.class) //수정 요구사항 추가
    private Long id;

    @NotBlank(groups = {SaveCheck.class, UpdateCheck.class})
    private String itemName;

    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    @Range(min = 1000, max = 1000000 , groups = {SaveCheck.class, UpdateCheck.class})
    private Integer price;   //typeMismatch가 되면 validation 검증하지 않는다.

    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    @Max(value = 9999, groups = SaveCheck.class)
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
