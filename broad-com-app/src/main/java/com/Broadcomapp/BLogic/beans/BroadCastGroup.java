package com.Broadcomapp.BLogic.beans;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name="broad_user_group", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"groupName","updatedBy"})
})
public class BroadCastGroup {
    @Id
    @SequenceGenerator(
            name="broadcast_group_sequence",
            sequenceName = "broadcast_group_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "broadcast_group_sequence"
    )
    @Column(name = "broad_cast_group_id")
    private Long broadCastGroupId;

    private String groupName;

    private String createdBy;
    private LocalDateTime createdDate;

    private String updatedBy;
    private LocalDateTime updatedDate;

    @ElementCollection
    private List<Integer> broadUsersIdList;
}
