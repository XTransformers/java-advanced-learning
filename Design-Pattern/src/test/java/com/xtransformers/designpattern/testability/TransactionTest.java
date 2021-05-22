package com.xtransformers.designpattern.testability;

import org.junit.Test;

import javax.transaction.InvalidTransactionException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TransactionTest {

    @Test
    public void executeTest1() throws InvalidTransactionException {
        WalletRpcService mockWalletRpcService = mock(WalletRpcService.class);
        when(mockWalletRpcService.moveMoney("t_1", 1L, 2L, 4D)).thenReturn("123abc");

        TransactionLock mockTransactionLock = mock(TransactionLock.class);
        when(mockTransactionLock.lock("t_1")).thenReturn(true);

        Long buyerId = 1L;
        Long sellerId = 2L;
        Long productId = 3L;
        String orderId = "456";
        Transaction transaction = new Transaction("1", buyerId, sellerId, productId, orderId);
        transaction.setAmount(4D);
        transaction.setWalletRpcService(mockWalletRpcService);
        transaction.setTransactionLock(mockTransactionLock);

        boolean executeResult = transaction.execute();
        assertTrue(executeResult);
        assertEquals(STATUS.EXECUTED, transaction.getStatus());
    }

    @Test
    public void testExecute_with_TransactionIsExpired() throws InvalidTransactionException {
        WalletRpcService mockWalletRpcService = mock(WalletRpcService.class);
        when(mockWalletRpcService.moveMoney("t_1", 1L, 2L, 4D)).thenReturn("123abc");

        TransactionLock mockTransactionLock = mock(TransactionLock.class);
        when(mockTransactionLock.lock("t_1")).thenReturn(true);

        Long buyerId = 1L;
        Long sellerId = 2L;
        Long productId = 3L;
        String orderId = "456";
        Transaction transaction = new Transaction("1", buyerId, sellerId, productId, orderId) {
            @Override
            protected boolean isExpired(long executionInvokedTimestamp) {
                return true;
            }
        };
        transaction.setAmount(4D);
        transaction.setWalletRpcService(mockWalletRpcService);
        transaction.setTransactionLock(mockTransactionLock);

        boolean actualResult = transaction.execute();
        assertFalse(actualResult);
        assertEquals(STATUS.EXPIRED, transaction.getStatus());
    }
}