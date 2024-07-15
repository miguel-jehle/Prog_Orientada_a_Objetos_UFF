package com.migueljehle.model;

import com.migueljehle.exception.DataInvalidaException;
import com.migueljehle.util.Id;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ExecVoo implements Serializable {
    @Id
    private int id;

    private ZonedDateTime dataHoraInicio;

    private ZonedDateTime dataHoraChegada;

    private Voo voo;

    private List<ExecTrecho> execsTrecho;

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public ExecVoo(String dataHoraInicio, String dataHoraChegada, Voo voo) throws DataInvalidaException{
        setDataHoraInicio(dataHoraInicio);
        setDataHoraChegada(dataHoraChegada);
        validarDatas();
        this.voo = voo;
        this.execsTrecho = new ArrayList<>();
    }

    public String toString(){
        return "ID = " + id +
                " | Data início = " + getDataHoraInicio() +
                " | Data chegada = " + getDataHoraChegada() +
                " | Voo = " + voo.getId();
    }

    public void setDataHoraInicio(String data) throws DataInvalidaException{

        try{
            int dia = Integer.parseInt(data.substring(0,2));
            int mes = Integer.parseInt(data.substring(3,5));
            int ano = Integer.parseInt(data.substring(6,10));
            int hora = Integer.parseInt(data.substring(11,13));
            int min = Integer.parseInt(data.substring(14,16));
            int seg = Integer.parseInt(data.substring(17,19));
            int mili = 0;

            this.dataHoraInicio = ZonedDateTime.of(
                    ano,
                    mes,
                    dia,
                    hora,
                    min,
                    seg,
                    mili,
                    ZoneId.of("America/Sao_Paulo")).withZoneSameInstant(ZoneId.of("UTC"));
        }
        catch (StringIndexOutOfBoundsException |
               NumberFormatException |
               DateTimeException e){

                throw new DataInvalidaException("Data de admissão inválida.");
        }
    }

    public void validarDatas() throws DataInvalidaException{
        if(dataHoraChegada.isBefore(dataHoraInicio)){
            throw new DataInvalidaException("PREZADO USUÁRIO: A data de partida não pode ser anterior a de chegada");
        }
    }

    public void setDataHoraChegada(String data) throws DataInvalidaException{

        try{
            int dia = Integer.parseInt(data.substring(0,2));
            int mes = Integer.parseInt(data.substring(3,5));
            int ano = Integer.parseInt(data.substring(6,10));
            int hora = Integer.parseInt(data.substring(11,13));
            int min = Integer.parseInt(data.substring(14,16));
            int seg = Integer.parseInt(data.substring(17,19));
            int mili = 0;

            this.dataHoraChegada = ZonedDateTime.of(
                    ano,
                    mes,
                    dia,
                    hora,
                    min,
                    seg,
                    mili,
                    ZoneId.of("America/Sao_Paulo")).withZoneSameInstant(ZoneId.of("UTC"));
        }
        catch (StringIndexOutOfBoundsException |
               NumberFormatException |
               DateTimeException e){
            throw new DataInvalidaException("Data de admissão inválida.");
        }
    }
    public String getDataHoraChegada() {
        return DTF.format(dataHoraChegada.withZoneSameInstant(ZoneId.of("America/Sao_Paulo")));
    }

    public String getDataHoraInicio() {
        return DTF.format(dataHoraInicio.withZoneSameInstant(ZoneId.of("America/Sao_Paulo")));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Voo getVoo() {
        return voo;
    }

    public void setVoo(Voo voo) {
        this.voo = voo;
    }

    public ZonedDateTime getInicio() {return dataHoraInicio;}

    public List<ExecTrecho> getExecsTrecho() {
        return execsTrecho;
    }

    public void setExecs(List<ExecTrecho> execsTrecho) {
        this.execsTrecho = execsTrecho;
    }
}
