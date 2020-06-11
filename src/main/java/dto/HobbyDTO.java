package dto;

import entities.Hobby;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nina
 */
public class HobbyDTO {

    private long id;
    private String name;
    private String description;

    public HobbyDTO() {
    }

    public HobbyDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public HobbyDTO(Hobby hobby) {
        this.id = hobby.getId();
        this.name = hobby.getName();
        this.description = hobby.getDescription();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static List<HobbyDTO> convertHobbyListToDTO(List<Hobby> hobbies) {
        List<HobbyDTO> hobbiesDTO = new ArrayList<>();
        for (Hobby hobby : hobbies) {
            hobbiesDTO.add(new HobbyDTO(hobby));
        }
        return hobbiesDTO;
    }

}
