package com.develop.jina1.adminPanel.characteristic;

import com.develop.jina1.adminPanel.category.categoryCharacteristic.CategoryCharValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("characteristic")
public class Characteristic {
    @Id
    private Long id;
    private String name;
    @Column("characteristic_id")
    private Set<CategoryCharValue> categoryCharValues;
    @Column("is_additional")
    private boolean isAdditional;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Characteristic that = (Characteristic) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
