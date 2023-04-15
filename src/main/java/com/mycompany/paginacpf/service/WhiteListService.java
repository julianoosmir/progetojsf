package com.mycompany.paginacpf.service;

import com.mycompany.paginacpf.model.DataForm;
import com.mycompany.paginacpf.model.ResponseDTO;
import com.mycompany.paginacpf.resources.Mydemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

public class WhiteListService {

    private List<Mydemo> mydemos = new ArrayList<>();

    public WhiteListService() {
        this.createMock();
    }

    public ResponseDTO runRequest(DataForm userDataForm) {
        List<Mydemo> mydemosfilter = this.mydemos.stream()
                .filter(demo -> demo.getCnpj().equals(userDataForm.getCpfCnpjPessoaVinculo()))
                .collect(Collectors.toList());

        Map<String, Boolean> map = new HashMap<>();
        map.put("whiteList",!mydemosfilter.isEmpty());
        return  new ResponseDTO(200, map.get("whiteList"));
    }

    private void createMock(){
        Mydemo mydemo = new Mydemo("37.983.811/0001-60","863.386.450-93");
        Mydemo mydemo1 = new Mydemo("41.910.404/0001-08","679.938.410-62");
        Mydemo mydemo2 = new Mydemo("31.951.408/0001-72","888.105.350-06");
        Mydemo mydemo3 = new Mydemo("96.704.729/0001-18","774.405.210-21");
        Mydemo mydemo4 = new Mydemo("48.833.771/0001-96","431.887.350-14");

        this.mydemos.addAll(Arrays.asList(mydemo1,mydemo2,mydemo,mydemo3,mydemo4));

    }
}
