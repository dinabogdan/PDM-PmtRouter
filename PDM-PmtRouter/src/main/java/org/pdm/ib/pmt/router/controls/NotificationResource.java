package org.pdm.ib.pmt.router.controls;

import org.pdm.ib.pmt.router.command.NotificationCommand;
import org.pdm.ib.pmt.router.entities.Tx;
import org.pdm.ib.pmt.router.repos.TxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationResource {

    private final TxRepository txRepository;

    @Autowired
    public NotificationResource(final TxRepository txRepository) {
        this.txRepository = txRepository;
    }

    @GetMapping("/notifications")
    public NotificationCommand doNotification() {
        Tx tx = txRepository.findFirstByOrderByIdDesc();
        tx.setNotified(true);
        txRepository.save(tx);
        return NotificationCommand.builder()
                .id(tx.getId())
                .notificationMessage("Transaction to " + tx.getName()
                        + " with amount of " + tx.getName()
                        + " was processed with success!")
                .hasNotification(true)
                .build();
    }
}
