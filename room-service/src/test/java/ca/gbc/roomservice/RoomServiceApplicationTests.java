package ca.gbc.roomservice;

import ca.gbc.roomservice.controller.RoomServiceController;
import ca.gbc.roomservice.dto.RoomServiceRequest;
import ca.gbc.roomservice.model.RoomServiceModel;
import ca.gbc.roomservice.service.RoomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RoomServiceController.class)
class RoomServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomService roomService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addRoom_ShouldReturnRoomServiceModel() throws Exception {
        List<String> features = Arrays.asList("Projector", "Whiteboard");
        RoomServiceRequest request = new RoomServiceRequest("Room1", 10, features, true);
        RoomServiceModel roomModel = new RoomServiceModel(1L, "Room1", 10, features, true);

        Mockito.when(roomService.addRoom(any(RoomServiceRequest.class))).thenReturn(roomModel);

        mockMvc.perform(post("/api/rooms")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.roomName").value("Room1"))
            .andExpect(jsonPath("$.capacity").value(10))
            .andExpect(jsonPath("$.features").isArray())
            .andExpect(jsonPath("$.features[0]").value("Projector"))
            .andExpect(jsonPath("$.features[1]").value("Whiteboard"))
            .andExpect(jsonPath("$.availability").value(true));
    }

    @Test
    void updateRoom_ShouldReturnUpdatedRoom() throws Exception {
        List<String> features = Arrays.asList("Projector");
        RoomServiceRequest request = new RoomServiceRequest("Room1 Updated", 15, features, false);
        RoomServiceModel updatedRoom = new RoomServiceModel(1L, "Room1 Updated", 15, features, false);

        Mockito.when(roomService.updateRoom(anyLong(), any(RoomServiceRequest.class))).thenReturn(updatedRoom);

        mockMvc.perform(put("/api/rooms/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.roomName").value("Room1 Updated"))
            .andExpect(jsonPath("$.capacity").value(15))
            .andExpect(jsonPath("$.features").isArray())
            .andExpect(jsonPath("$.features[0]").value("Projector"))
            .andExpect(jsonPath("$.availability").value(false));
    }

    @Test
    void getAllRooms_ShouldReturnListOfRooms() throws Exception {
        List<String> features1 = Arrays.asList("Projector", "Whiteboard");
        List<String> features2 = Arrays.asList("Whiteboard");
        RoomServiceModel room1 = new RoomServiceModel(1L, "Room1", 10, features1, true);
        RoomServiceModel room2 = new RoomServiceModel(2L, "Room2", 20, features2, false);
        List<RoomServiceModel> rooms = Arrays.asList(room1, room2);

        Mockito.when(roomService.getAllRooms()).thenReturn(rooms);

        mockMvc.perform(get("/api/rooms"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].roomName").value("Room1"))
            .andExpect(jsonPath("$[1].roomName").value("Room2"));
    }

    @Test
    void getRoomById_ShouldReturnRoomById() throws Exception {
        List<String> features = Arrays.asList("Projector", "Whiteboard");
        RoomServiceModel room = new RoomServiceModel(1L, "Room1", 10, features, true);

        Mockito.when(roomService.getRoomById(anyLong())).thenReturn(room);

        mockMvc.perform(get("/api/rooms/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.roomName").value("Room1"))
            .andExpect(jsonPath("$.capacity").value(10))
            .andExpect(jsonPath("$.features").isArray())
            .andExpect(jsonPath("$.features[0]").value("Projector"))
            .andExpect(jsonPath("$.features[1]").value("Whiteboard"));
    }

    @Test
    void checkRoomAvailability_ShouldReturnRoomAvailability() throws Exception {
        Mockito.when(roomService.checkRoomAvailability("Room1")).thenReturn(true);

        mockMvc.perform(get("/api/rooms/availability/Room1"))
            .andExpect(status().isOk())
            .andExpect(content().string("true"));
    }
}