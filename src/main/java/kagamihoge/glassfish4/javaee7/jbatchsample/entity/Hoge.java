package kagamihoge.glassfish4.javaee7.jbatchsample.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hoge")
public class Hoge implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "hoge_id")
    private Integer hogeId;

    @Column(name = "hoge_value")
    private String hogeValue;

    public Hoge() {
    }
    
    public Hoge(Integer hogeId, String hogeValue) {
        super();
        this.hogeId = hogeId;
        this.hogeValue = hogeValue;
    }

    public Integer getHogeId() {
        return hogeId;
    }

    public void setHogeId(Integer hogeId) {
        this.hogeId = hogeId;
    }

    public String getHogeValue() {
        return hogeValue;
    }

    public void setHogeValue(String hogeValue) {
        this.hogeValue = hogeValue;
    }
    

}
