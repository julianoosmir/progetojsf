package com.mycompany.paginacpf.service;

import com.mycompany.paginacpf.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MethodMock {

    private List<SeachMock> seachMockList = new ArrayList<>();

    public ResponseDTO runRequest(DataForm userDataForm) {

        List<ContextModel> contextModels = buscarCpf(userDataForm.getPersonalData().getCpfUsuario());
        List<ContextDto> dtos = converterToDto(contextModels);

        return new ResponseDTO(200, dtos);
    }

    private List<ContextDto> converterToDto(List<ContextModel> contextModels) {
        List<ContextDto> dtos = new ArrayList<>();
        for (ContextModel contexto : contextModels) {
            ContextDto dto = new ContextDto(contexto);
            dtos.add(dto);
        }
        return dtos;
    }

    private List<ContextModel> buscarCpf(String cpf) {
        this.preecher();
        List<ContextModel> contextModel = new ArrayList<>();
        for (SeachMock seachMock : this.seachMockList) {
            if (seachMock.getCpf() == cpf) {
                contextModel = seachMock.getContextModelList();
            }
        }
        return contextModel;
    }

    private void preecher() {
        ContextModel model1 = new ContextModel("ZBS009");
        ContextModel model2 = new ContextModel("ANG002");
        ContextModel model3 = new ContextModel("ZBS007_I");
        List<ContextModel> contextModels1 = Arrays.asList(model1, model2, model3);

        ContextModel model4 = new ContextModel("INS001");
        ContextModel model5 = new ContextModel("ZBS011_S");
        List<ContextModel> contextModels2 = Arrays.asList(model4, model5);

        SeachMock seachMock1 = new SeachMock("08418621842", contextModels1);
        SeachMock seachMock2 = new SeachMock("00000000272", contextModels2);
        this.seachMockList = Arrays.asList(seachMock1, seachMock2);

    }

}
