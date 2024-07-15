package com.migueljehle.model;

import com.migueljehle.exception.DataIncoerenteExecption;
import com.migueljehle.exception.DataInvalidaException;
import com.migueljehle.util.Id;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ExecTrecho implements Serializable {

    @Id
    private int id;

    private ZonedDateTime dataHoraInicio;

    private ZonedDateTime dataHoraChegada;

    private ExecVoo execVoo;

    private Trecho trecho;

    private List<Passagem> passagens;

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public ExecTrecho(String dataHoraInicio, String dataHorachegada, ExecVoo execVoo, Trecho trecho)throws DataInvalidaException{
        setDataHoraChegada(dataHorachegada);
        setDataHoraInicio(dataHoraInicio);
        validarDatas();
        if(execVoo.getExecsTrecho().isEmpty()){ //Se for o primeiro ver se bate com a inicial da exec voo correspondente
            if(!this.dataHoraInicio.isEqual(execVoo.getInicio())){
                throw new DataInvalidaException("\n A data de início da execução de trecho não corresponde a data de inicio da execução de voo!");
            }
        }
        else{ //Caso não for, ver se a data de inicio atual, é posterior a data de fim do último cadastrado.
            List<ExecTrecho> execTrechos = execVoo.getExecsTrecho();
            ExecTrecho last = execTrechos.get(execTrechos.size() - 1);
            validarDatas();
            //if(last.getDataHoraChegadaZ().isBefore(this.dataHoraInicio)){
            //    throw new DataInvalidaException("Data de inicio anterior a ultima data de termino cadastrada!");
            //}
        }
        this.execVoo = execVoo;
        this.trecho = trecho;
        this.passagens = new ArrayList<>();
    }

    public void validarDatas() throws DataInvalidaException{
        if(dataHoraChegada.isBefore(dataHoraInicio)){
            throw new DataInvalidaException("\n PREZADO USUÁRIO: A data de partida não pode ser anterior a de chegada");
        }
    }
    public boolean posData(String data, String comparacao) {
            int dia = Integer.parseInt(data.substring(0,2));
            int mes = Integer.parseInt(data.substring(3,5));
            int ano = Integer.parseInt(data.substring(6,10));

            int hora =    Integer.parseInt(data.substring(11,13));
            int minuto =  Integer.parseInt(data.substring(14,16));
            int segundo = Integer.parseInt(data.substring(17,19));

            ZonedDateTime dataDigitada = ZonedDateTime.of(
                    ano, mes, dia, hora, minuto, segundo, 0,
                    ZoneId.of("America/Sao_Paulo")).withZoneSameInstant(ZoneId.of("UTC"));

            dia = Integer.parseInt(data.substring(0,2));
            mes = Integer.parseInt(data.substring(3,5));
            ano = Integer.parseInt(data.substring(6,10));

            hora =    Integer.parseInt(data.substring(11,13));
            minuto =  Integer.parseInt(data.substring(14,16));
            segundo = Integer.parseInt(data.substring(17,19));

            ZonedDateTime dataExectrecho = ZonedDateTime.of(
                    ano, mes, dia, hora, minuto, segundo, 0,
                    ZoneId.of("America/Sao_Paulo")).withZoneSameInstant(ZoneId.of("UTC"));

            boolean ver = false;
            if(dataExectrecho.isAfter(dataDigitada)) ver = true;
            if(dataExectrecho.isEqual(dataDigitada)) ver = true;
            return ver;
    }

    public boolean viagemRealizada(){
        ZonedDateTime agora = ZonedDateTime.now(ZoneId.of("UTC"));
        return dataHoraInicio.isBefore(agora);
    }

    public String toString(){
        return "ID = " + id +
                " | Data início = " + getDataHoraInicio() +
                " | Data chegada = " + getDataHoraChegada() +
                " | Execução Voo = " + execVoo.getId() +
                " | Trecho = " + trecho.getId();
    }

    public void setDataHoraInicio(String data) throws DataInvalidaException {

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

            throw new DataInvalidaException("PREZADO USUÁRIO: Data de admissão inválida.");
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
            throw new DataInvalidaException("PREZADO USUÁRIO: Data de admissão inválida.");
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

    public Trecho getTrecho(){
        return trecho;
    }

    public void setTrecho(Trecho trecho){
        this.trecho = trecho;
    }

    public ExecVoo getExecVoo(){
        return execVoo;
    }

    public void setExecVoo(ExecVoo execVoo){
        this.execVoo = execVoo;
    }

    public List<Passagem> getPassagens(){ return passagens;}

}
