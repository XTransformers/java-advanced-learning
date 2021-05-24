package com.xtransformers.designpattern.collector.refactor;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;

/**
 * @author daniel
 * @date 2021-05-23
 */
public class EmailViewer implements StatViewer {

    private EmailSender emailSender;
    private List<String> toAddresses = Lists.newArrayList();

    public EmailViewer() {
        emailSender = new EmailSender();
    }

    public EmailViewer(List<String> toAddresses) {
        this.toAddresses = toAddresses;
        emailSender = new EmailSender();
    }

    public void addAddress(String email) {
        toAddresses.add(email);
    }

    @Override
    public void output(Map<String, RequestStat> requestStats, long startInMillis, long endInMillis) {
        // format the requestStats to HTML style.
        // send it to email toAddresses.

    }
}
