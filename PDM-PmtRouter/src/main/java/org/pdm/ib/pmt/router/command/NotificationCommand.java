package org.pdm.ib.pmt.router.command;

import lombok.*;

import java.io.Serializable;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NotificationCommand implements Serializable {

    private Long id;
    private String notificationMessage;
    private Boolean hasNotification;
}
