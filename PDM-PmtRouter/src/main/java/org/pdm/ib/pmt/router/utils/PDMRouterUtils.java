package org.pdm.ib.pmt.router.utils;

import org.pdm.ib.pmt.router.entities.Transaction;

import java.util.*;

public class PDMRouterUtils {

    public static List<Transaction> orderTransactions(List<Transaction> transactions) {
        Collections.sort(transactions, new Comparator<Transaction>() {
            @Override
            public int compare(Transaction tx1, Transaction tx2) {
                return tx1.getProcessDate().compareTo(tx2.getProcessDate());
            }
        });
        return transactions;
    }
}
