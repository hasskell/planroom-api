package com.planroom.app.service;

import org.springframework.util.StringUtils;

import java.text.Normalizer;

public class DisplayNameService {

    private DisplayNameService() {}

    private static DisplayNameService instance;

    public static DisplayNameService of(){
        return instance == null ? instance = new DisplayNameService() : instance;
    }

    public String normalizeOrDefault(String candidate, String suffix) {
        String name = (candidate == null) ? "" : Normalizer.normalize(candidate.trim(), Normalizer.Form.NFKC);
        if (!StringUtils.hasText(name)) return "Guest-" + suffix;
        String safe = name.replaceAll("[^A-Za-z0-9 _.-]", "");
        if (safe.length() < 2) safe = (safe + "??");
        if (safe.length() > 32) safe = safe.substring(0, 32);
        return safe;
    }
}
