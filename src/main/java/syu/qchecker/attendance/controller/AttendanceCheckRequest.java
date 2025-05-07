// syu.qchecker.attendance.controller.AttendanceCheckRequest.java
package syu.qchecker.attendance.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AttendanceCheckRequest {
    private Long eventId;
    private String qrcode;
    private Double latitude;
    private Double longitude;
}
