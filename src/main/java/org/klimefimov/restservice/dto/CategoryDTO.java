package org.klimefimov.restservice.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class CategoryDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 6393428176866329L;
    private final int id;
    private final String name;
    private final String type;

    public CategoryDTO(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryDTO that = (CategoryDTO) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type);
    }

    //    public static List<CategoryDTO> getDTO(PGobject pGobject) {  //Это должно быть в другом месте
//        List<CategoryDTO> list = new ArrayList<>();
//        try {
//            JsonNode jsonNode = new ObjectMapper().readTree(pGobject.getValue());
//            jsonNode.elements().forEachRemaining(node -> {
//                int id = node.get("id").asInt();
//                String name = node.get("name").asText();
//                String type = node.get("type").asText();  // FIX
//                CategoryDTO dto = new CategoryDTO(id, name, type);
//                list.add(dto);
//            });
//        } catch (Exception e) {e.printStackTrace();}
//        return list;
//    }


}
