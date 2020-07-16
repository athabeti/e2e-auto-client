package com.visa.rtp.e2e.remotedirtesttool.Service;

import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visa.rtp.e2e.remotedirtesttool.Exception.FetApiRequestBodyException;
import com.visa.rtp.e2e.remotedirtesttool.Exception.FetchAiasResponseException;
import com.visa.rtp.e2e.remotedirtesttool.Exception.FetchAliasNoDataException;
import com.visa.rtp.e2e.remotedirtesttool.Model.FetchAliasRequest;
import com.visa.rtp.e2e.remotedirtesttool.Model.FetchAliasResponse;
import com.visa.rtp.e2e.remotedirtesttool.Model.FetchResponseEntity;
import com.visa.rtp.e2e.remotedirtesttool.Repo.FetchRepository;
import com.visa.rtp.e2e.remotedirtesttool.Util.CheckAliasType;

@Service
public class FetchV2ApiServices {

    @Autowired
    private FetchRepository fetchRepo;

    FetchAliasResponseWithEntity fetchAlisRespEntyMap = new FetchAliasResponseWithEntity();
    private boolean isRecordExist = false;
    FetchAliasResponse response = new FetchAliasResponse();
    CheckAliasType checkAlias = new CheckAliasType();
    FetchApiRequestValidator fetchReqVal;
    TreeMap<Long, FetchResponseEntity> responseMapObj = new TreeMap<Long, FetchResponseEntity>();

    public FetchAliasResponse getV2AliasData(FetchAliasRequest inputReq) throws FetApiRequestBodyException {

        System.out.println(" input request :: " + inputReq.toString());

        fetchReqVal = new FetchApiRequestValidator(inputReq);
        fetchReqVal.validateAllInputRequest();

        System.out.println(" after request validation :: ");

        responseMapObj.clear();

        if (inputReq.getAlias() != null && (inputReq.getAliasType() == null) && (inputReq.getDirectoryType() == null)
                && (inputReq.getOriginatorCountry() == null)) {

            System.out.println(" ******************** In condition where alise is only in the request ");

            response = getDetailsWithAliasOnly(inputReq);

        } else if (inputReq.getAlias() != null && (inputReq.getAliasType() == null)
                && (inputReq.getDirectoryType() != null) && (inputReq.getOriginatorCountry() == null)) {

            System.out.println("************ in condition where alias and directory type in request ");

            response = getDetailsWithAliasDirectoryType(inputReq);

        } else if (inputReq.getAlias() != null && (inputReq.getAliasType() == null)
                && (inputReq.getDirectoryType() != null) && (inputReq.getOriginatorCountry() != null)) {

            System.out
                    .println("************ in condition where alias, directorytype and OriginatorCountry in request ");

            // response = getDetailsWithAliasDirectoryTypeAndOriginCountry(inputReq);
            response = getDetailsWithAliasDirectoryType(inputReq);

        } else if (inputReq.getAlias() != null && (inputReq.getAliasType() == null)
                && (inputReq.getDirectoryType() == null) && (inputReq.getOriginatorCountry() != null)) {

            System.out.println("************ in condition where alias and OriginatorCountry in request ");

            // response = getDetailsWithAliasOriginCountry(inputReq);
            response = getDetailsWithAliasOnly(inputReq);

        } else if ((inputReq.getAlias() != null) && (inputReq.getAliasType() != null)
                && (inputReq.getDirectoryName() == null) && (inputReq.getDirectoryType() == null)
                && (inputReq.getOriginatorCountry() == null)) {

            System.out.println(" ******************** In condition where alise and type are in request  ");

            if (checkAlias.isAliasTypeExist(inputReq.getAliasType())) {
                System.out.println(inputReq.getAliasType() + " ::  given type is in the list :: true ");
                response = getDetailsWithAliasAndType(inputReq);
            } else {

                System.out.println(inputReq.getAliasType() + " :: given type is in the list :: false ");
                response = getDetailsWithAliasOnly(inputReq);
            }

        } else if ((inputReq.getAlias() != null) && (inputReq.getAliasType() != null)
                && (inputReq.getDirectoryName() == null) && (inputReq.getDirectoryType() != null)
                && (inputReq.getOriginatorCountry() == null)) {

            System.out
                    .println(" ******************** In condition where alias, aliasType & directoryType in request  ");

            if (checkAlias.isAliasTypeExist(inputReq.getAliasType())) {
                System.out.println(inputReq.getAliasType() + " ::  given type is in the list :: true ");
                response = getDetailsWithAliasAndTypeDirectorytype(inputReq);
            } else {

                System.out.println(inputReq.getAliasType() + " :: given type is in the list :: false ");
                response = getDetailsWithAliasDirectoryType(inputReq);
            }

        } else if ((inputReq.getAlias() != null) && (inputReq.getAliasType() != null)
                && (inputReq.getDirectoryName() == null) && (inputReq.getDirectoryType() != null)
                && (inputReq.getOriginatorCountry() != null)) {

            System.out.println(
                    " ******************** In condition where alias, aliasType , directoryType and OriginatorCountry"
                            + " in request  ");

            if (checkAlias.isAliasTypeExist(inputReq.getAliasType())) {
                System.out.println(inputReq.getAliasType() + " ::  given type is in the list :: true ");
                // response = getDetailsWithAliasAndTypeDirectorytypeOriginCountry(inputReq);
                response = getDetailsWithAliasAndTypeDirectorytype(inputReq);
            } else {

                System.out.println(inputReq.getAliasType() + " :: given type is in the list :: false ");
                // response = getDetailsWithAliasDirectoryTypeAndOriginCountry(inputReq);
                response = getDetailsWithAliasDirectoryType(inputReq);
            }

        } else if ((inputReq.getAlias() != null) && (inputReq.getAliasType() != null)
                && (inputReq.getDirectoryName() == null) && (inputReq.getDirectoryType() == null)
                && (inputReq.getOriginatorCountry() != null)) {

            System.out.println(" ******************** In condition where alias, aliasType ,  and OriginatorCountry"
                    + " in request  ");

            if (checkAlias.isAliasTypeExist(inputReq.getAliasType())) {
                System.out.println(inputReq.getAliasType() + " ::  given type is in the list :: true ");
                // response = getDetailsWithAliasAndTypeOriginCountry(inputReq);
                response = getDetailsWithAliasAndType(inputReq);
            } else {

                System.out.println(inputReq.getAliasType() + " :: given type is in the list :: false ");
                // response = getDetailsWithAliasOriginCountry(inputReq);
                response = getDetailsWithAliasOnly(inputReq);
            }

        }

        else if ((inputReq.getAlias() != null) && (inputReq.getAliasType() != null)
                && (inputReq.getDirectoryName() != null) && (inputReq.getDirectoryType() != null)
                && (inputReq.getOriginatorCountry() != null)) {

            System.out.println(" ******************** In condition where alias, aliasType , directoryName ,"
                    + "directoryType  and OriginatorCountry" + " in request  ");

            if (checkAlias.isAliasTypeExist(inputReq.getAliasType())) {
                System.out.println(inputReq.getAliasType() + " ::  given type is in the list :: true ");
                // response =
                // getDetailsWithAliasAndTypeDirectoryNameDirectorytypeOriginCountry(inputReq);
                response = getDetailsWithAliasAndTypeDirectorytype(inputReq);
            } else {

                System.out.println(inputReq.getAliasType() + " :: given type is in the list :: false ");
                // response =
                // getDetailsWithAliasDirectoryNameDirectorytypeOriginCountry(inputReq);
                response = getDetailsWithAliasDirectoryType(inputReq);
            }

        } else if ((inputReq.getAlias() != null) && (inputReq.getAliasType() != null)
                && (inputReq.getDirectoryName() != null) && (inputReq.getDirectoryType() != null)
                && (inputReq.getOriginatorCountry() == null)) {

            System.out.println(" ******************** In condition where alias, aliasType , directoryName ,"
                    + "  and OriginatorCountry" + " in request  ");

            if (checkAlias.isAliasTypeExist(inputReq.getAliasType())) {
                System.out.println(inputReq.getAliasType() + " ::  given type is in the list :: true ");
                // response = getDetailsWithAliasAndTypeDirectoryNameDirectorytype(inputReq);
                response = getDetailsWithAliasAndTypeDirectorytype(inputReq);
            } else {

                System.out.println(inputReq.getAliasType() + " :: given type is in the list :: false ");
                // response = getDetailsWithAliasDirectoryNameDirectorytype(inputReq);
                response = getDetailsWithAliasDirectoryType(inputReq);
            }

        } else if ((inputReq.getAlias() != null) && (inputReq.getAliasType() != null)
                && (inputReq.getDirectoryName() != null) && (inputReq.getDirectoryType() == null)
                && (inputReq.getOriginatorCountry() == null)) {

            System.out.println(
                    " ******************** In condition where alias, aliasType , directoryName ," + " in request  ");
            if (checkAlias.isAliasTypeExist(inputReq.getAliasType())) {
                System.out.println(inputReq.getAliasType() + " ::  given type is in the list :: true ");
                // response = getDetailsWithAliasAndTypeDirectoryName(inputReq);
                response = getDetailsWithAliasAndType(inputReq);

            } else {

                System.out.println(inputReq.getAliasType() + " :: given type is in the list :: false ");
                // response = getDetailsWithAliasAndDirectoryName(inputReq);
                response = getDetailsWithAliasOnly(inputReq);
            }

        } else if ((inputReq.getAlias() != null) && (inputReq.getAliasType() != null)
                && (inputReq.getDirectoryName() != null) && (inputReq.getDirectoryType() == null)
                && (inputReq.getOriginatorCountry() != null)) {

            System.out.println(" ******************** In condition where alias, aliasType , directoryName ,"
                    + "  and OriginatorCountry" + " in request  ");

            if (checkAlias.isAliasTypeExist(inputReq.getAliasType())) {
                System.out.println(inputReq.getAliasType() + " ::  given type is in the list :: true ");
                // response = getDetailsWithAliasAndTypeDirectoryNameOriginCountry(inputReq);

                response = getDetailsWithAliasAndType(inputReq);
            } else {

                System.out.println(inputReq.getAliasType() + " :: given type is in the list :: false ");
                // response = getDetailsWithAliasAndDirectoryNameOriginCountry(inputReq);
                response = getDetailsWithAliasOnly(inputReq);
            }

        }

        else {

            System.out.println(" ******************** :: Wrong Request ");
        }

        return response;
    }

    public FetchAliasResponse getDetailsWithAliasAndType(FetchAliasRequest inputReq)
            throws FetchAliasNoDataException, FetchAiasResponseException {
        FetchAliasResponse faresp = null;

        System.out.println("getDetailsWithAliasAndType :: ");

        try {

            for (FetchResponseEntity fchRespIterator : fetchRepo.findAll()) {

                if ((fchRespIterator.getAlias().equals(inputReq.getAlias()))
                        && (fchRespIterator.getAliasType().equalsIgnoreCase(inputReq.getAliasType()))) {
                    // faresp = fetchAlisRespEntyMap.mapResponseObject(fchRespIterator);
                    isRecordExist = true;
                    // break;
                    responseMapObj.put(fchRespIterator.getId(), fchRespIterator);

                }
            }
            System.out.println(" is the flag :: " + isRecordExist);

            NoDataHadle(isRecordExist);

        } catch (FetApiRequestBodyException e) {

            throw new FetchAiasResponseException(e.toString());
        }

        return fetchAlisRespEntyMap.mapResponseObject(returnlatestEntry());
    }

    public FetchAliasResponse getDetailsWithAliasAndTypeDirectorytype(FetchAliasRequest inputReq)
            throws FetchAliasNoDataException, FetchAiasResponseException {
        FetchAliasResponse faresp = null;

        System.out.println("getDetailsWithAliasAndTypeDirectorytype :: ");

        try {

            for (FetchResponseEntity fchRespIterator : fetchRepo.findAll()) {

                if ((fchRespIterator.getAlias().equals(inputReq.getAlias()))
                        && (fchRespIterator.getAliasType().equalsIgnoreCase(inputReq.getAliasType()))
                        && (fchRespIterator.getNetworkType().equalsIgnoreCase(inputReq.getDirectoryType()))) {
                    // faresp = fetchAlisRespEntyMap.mapResponseObject(fchRespIterator);
                    isRecordExist = true;
                    // break;
                    responseMapObj.put(fchRespIterator.getId(), fchRespIterator);

                }
            }
            System.out.println(" is the flag :: " + isRecordExist);

            NoDataHadle(isRecordExist);

        } catch (FetApiRequestBodyException e) {

            throw new FetchAiasResponseException(e.toString());
        }

        return fetchAlisRespEntyMap.mapResponseObject(returnlatestEntry());
    }

    public FetchAliasResponse getDetailsWithAliasAndTypeOriginCountry(FetchAliasRequest inputReq)
            throws FetchAliasNoDataException, FetchAiasResponseException {
        FetchAliasResponse faresp = null;

        System.out.println("getDetailsWithAliasAndTypeOriginCountry :: ");

        try {

            for (FetchResponseEntity fchRespIterator : fetchRepo.findAll()) {

                if ((fchRespIterator.getAlias().equals(inputReq.getAlias()))
                        && (fchRespIterator.getAliasType().equalsIgnoreCase(inputReq.getAliasType()))
                        && (fchRespIterator.getCountry().equalsIgnoreCase(inputReq.getOriginatorCountry()))) {
                    // faresp = fetchAlisRespEntyMap.mapResponseObject(fchRespIterator);
                    isRecordExist = true;
                    // break;
                    responseMapObj.put(fchRespIterator.getId(), fchRespIterator);

                }
            }
            System.out.println(" is the flag :: " + isRecordExist);

            NoDataHadle(isRecordExist);

        } catch (FetApiRequestBodyException e) {

            throw new FetchAiasResponseException(e.toString());
        }

        return fetchAlisRespEntyMap.mapResponseObject(returnlatestEntry());
    }

    public FetchAliasResponse getDetailsWithAliasAndTypeDirectorytypeOriginCountry(FetchAliasRequest inputReq)
            throws FetchAliasNoDataException, FetchAiasResponseException {
        FetchAliasResponse faresp = null;

        System.out.println("getDetailsWithAliasAndTypeDirectorytypeOriginCountry :: ");

        try {

            for (FetchResponseEntity fchRespIterator : fetchRepo.findAll()) {

                if ((fchRespIterator.getAlias().equals(inputReq.getAlias()))
                        && (fchRespIterator.getAliasType().equalsIgnoreCase(inputReq.getAliasType()))
                        && (fchRespIterator.getNetworkType().equalsIgnoreCase(inputReq.getDirectoryType()))
                        && (fchRespIterator.getCountry().equalsIgnoreCase(inputReq.getOriginatorCountry()))) {
                    // faresp = fetchAlisRespEntyMap.mapResponseObject(fchRespIterator);
                    isRecordExist = true;
                    // break;
                    responseMapObj.put(fchRespIterator.getId(), fchRespIterator);

                }
            }
            System.out.println(" is the flag :: " + isRecordExist);

            NoDataHadle(isRecordExist);

        } catch (FetApiRequestBodyException e) {

            throw new FetchAiasResponseException(e.toString());
        }

        return fetchAlisRespEntyMap.mapResponseObject(returnlatestEntry());
    }

    public FetchAliasResponse getDetailsWithAliasAndTypeDirectoryNameDirectorytypeOriginCountry(
            FetchAliasRequest inputReq) throws FetchAliasNoDataException, FetchAiasResponseException {
        FetchAliasResponse faresp = null;

        System.out.println("getDetailsWithAliasAndTypeDirectoryNameDirectorytypeOriginCountry :: ");

        try {

            for (FetchResponseEntity fchRespIterator : fetchRepo.findAll()) {

                if ((fchRespIterator.getAlias().equals(inputReq.getAlias()))
                        && (fchRespIterator.getAliasType().equalsIgnoreCase(inputReq.getAliasType()))
                        && (fchRespIterator.getNetworkName().equalsIgnoreCase(inputReq.getDirectoryName()))
                        && (fchRespIterator.getNetworkType().equalsIgnoreCase(inputReq.getDirectoryType()))
                        && (fchRespIterator.getCountry().equalsIgnoreCase(inputReq.getOriginatorCountry()))) {
                    // faresp = fetchAlisRespEntyMap.mapResponseObject(fchRespIterator);
                    isRecordExist = true;
                    // break;
                    responseMapObj.put(fchRespIterator.getId(), fchRespIterator);

                }
            }
            System.out.println(" is the flag :: " + isRecordExist);

            NoDataHadle(isRecordExist);

        } catch (FetApiRequestBodyException e) {

            throw new FetchAiasResponseException(e.toString());
        }

        return fetchAlisRespEntyMap.mapResponseObject(returnlatestEntry());
    }

    public FetchAliasResponse getDetailsWithAliasAndTypeDirectoryName(FetchAliasRequest inputReq)
            throws FetchAliasNoDataException, FetchAiasResponseException {
        FetchAliasResponse faresp = null;

        System.out.println("getDetailsWithAliasAndTypeDirectoryName :: ");

        try {

            for (FetchResponseEntity fchRespIterator : fetchRepo.findAll()) {

                if ((fchRespIterator.getAlias().equals(inputReq.getAlias()))
                        && (fchRespIterator.getAliasType().equalsIgnoreCase(inputReq.getAliasType()))
                        && (fchRespIterator.getNetworkName().equalsIgnoreCase(inputReq.getDirectoryName()))) {
                    // faresp = fetchAlisRespEntyMap.mapResponseObject(fchRespIterator);
                    isRecordExist = true;
                    // break;
                    responseMapObj.put(fchRespIterator.getId(), fchRespIterator);

                }
            }
            System.out.println(" is the flag :: " + isRecordExist);

            NoDataHadle(isRecordExist);

        } catch (FetApiRequestBodyException e) {

            throw new FetchAiasResponseException(e.toString());
        }

        return fetchAlisRespEntyMap.mapResponseObject(returnlatestEntry());
    }

    public FetchAliasResponse getDetailsWithAliasAndTypeDirectoryNameDirectorytype(FetchAliasRequest inputReq)
            throws FetchAliasNoDataException, FetchAiasResponseException {
        FetchAliasResponse faresp = null;

        System.out.println("getDetailsWithAliasAndTypeDirectoryNameDirectorytype :: ");

        try {

            for (FetchResponseEntity fchRespIterator : fetchRepo.findAll()) {

                if ((fchRespIterator.getAlias().equals(inputReq.getAlias()))
                        && (fchRespIterator.getAliasType().equalsIgnoreCase(inputReq.getAliasType()))
                        && (fchRespIterator.getNetworkName().equalsIgnoreCase(inputReq.getDirectoryName()))
                        && (fchRespIterator.getNetworkType().equalsIgnoreCase(inputReq.getDirectoryType()))) {
                    // faresp = fetchAlisRespEntyMap.mapResponseObject(fchRespIterator);
                    isRecordExist = true;
                    // break;
                    responseMapObj.put(fchRespIterator.getId(), fchRespIterator);

                }
            }
            System.out.println(" is the flag :: " + isRecordExist);

            NoDataHadle(isRecordExist);

        } catch (FetApiRequestBodyException e) {

            throw new FetchAiasResponseException(e.toString());
        }

        return fetchAlisRespEntyMap.mapResponseObject(returnlatestEntry());
    }

    public FetchAliasResponse getDetailsWithAliasAndTypeDirectoryNameOriginCountry(FetchAliasRequest inputReq)
            throws FetchAliasNoDataException, FetchAiasResponseException {
        FetchAliasResponse faresp = null;

        System.out.println("getDetailsWithAliasAndTypeDirectoryNameOriginCountry :: ");

        try {

            for (FetchResponseEntity fchRespIterator : fetchRepo.findAll()) {

                if ((fchRespIterator.getAlias().equals(inputReq.getAlias()))
                        && (fchRespIterator.getAliasType().equalsIgnoreCase(inputReq.getAliasType())
                                && (fchRespIterator.getNetworkName().equalsIgnoreCase(inputReq.getDirectoryName()))
                                && (fchRespIterator.getCountry().equalsIgnoreCase(inputReq.getOriginatorCountry())))) {
                    // faresp = fetchAlisRespEntyMap.mapResponseObject(fchRespIterator);
                    isRecordExist = true;
                    // break;
                    responseMapObj.put(fchRespIterator.getId(), fchRespIterator);

                }
            }
            System.out.println(" is the flag :: " + isRecordExist);

            NoDataHadle(isRecordExist);

        } catch (FetApiRequestBodyException e) {

            throw new FetchAiasResponseException(e.toString());
        }

        return fetchAlisRespEntyMap.mapResponseObject(returnlatestEntry());
    }

    public FetchAliasResponse getDetailsWithAliasOnly(FetchAliasRequest inputReq)
            throws FetchAliasNoDataException, FetchAiasResponseException {
        FetchAliasResponse faresp = null;
        System.out.println("getDetailsWithAliasOnly :: ");
        try {
            for (FetchResponseEntity fchRespIterator : fetchRepo.findAll()) {

                if (fchRespIterator.getAlias().equals(inputReq.getAlias())) {
                    isRecordExist = true;
                    // faresp = fetchAlisRespEntyMap.mapResponseObject(fchRespIterator);
                    // break;
                    responseMapObj.put(fchRespIterator.getId(), fchRespIterator);
                }

            }
            System.out.println(" is the flag :: " + isRecordExist);
            NoDataHadle(isRecordExist);
        } catch (FetApiRequestBodyException e) {
            throw new FetchAiasResponseException(e.toString());
        }
        // System.out.println(" faresp :: " + faresp.toString());

        return fetchAlisRespEntyMap.mapResponseObject(returnlatestEntry());

    }

    public FetchAliasResponse getDetailsWithAliasDirectoryType(FetchAliasRequest inputReq)
            throws FetchAliasNoDataException, FetchAiasResponseException {
        FetchAliasResponse faresp = null;
        System.out.println("getDetailsWithAliasDirectoryType :: ");
        try {
            for (FetchResponseEntity fchRespIterator : fetchRepo.findAll()) {

                if (fchRespIterator.getAlias().equals(inputReq.getAlias())
                        && (fchRespIterator.getNetworkType().equalsIgnoreCase(inputReq.getDirectoryType()))) {
                    isRecordExist = true;
                    // faresp = fetchAlisRespEntyMap.mapResponseObject(fchRespIterator);
                    // break;
                    responseMapObj.put(fchRespIterator.getId(), fchRespIterator);
                }

            }
            // System.out.println(" is the flag :: " + isRecordExist);
            NoDataHadle(isRecordExist);
        } catch (FetApiRequestBodyException e) {
            throw new FetchAiasResponseException(e.toString());
        }
        // System.out.println(" faresp :: " + faresp.toString());

        return fetchAlisRespEntyMap.mapResponseObject(returnlatestEntry());

    }

    public FetchAliasResponse getDetailsWithAliasDirectoryTypeAndOriginCountry(FetchAliasRequest inputReq)
            throws FetchAliasNoDataException, FetchAiasResponseException {
        FetchAliasResponse faresp = null;
        System.out.println("getDetailsWithAliasDirectoryTypeAndOrginCountry :: ");
        try {
            for (FetchResponseEntity fchRespIterator : fetchRepo.findAll()) {

                if (fchRespIterator.getAlias().equals(inputReq.getAlias())
                        && (fchRespIterator.getNetworkType().equalsIgnoreCase(inputReq.getDirectoryType())
                                && (fchRespIterator.getCountry().equalsIgnoreCase(inputReq.getOriginatorCountry())))) {
                    isRecordExist = true;
                    // faresp = fetchAlisRespEntyMap.mapResponseObject(fchRespIterator);
                    // break;
                    responseMapObj.put(fchRespIterator.getId(), fchRespIterator);
                }

            }
            System.out.println(" is the flag :: " + isRecordExist);
            NoDataHadle(isRecordExist);
        } catch (FetApiRequestBodyException e) {
            throw new FetchAiasResponseException(e.toString());
        }
        // System.out.println(" faresp :: " + faresp.toString());

        return fetchAlisRespEntyMap.mapResponseObject(returnlatestEntry());

    }

    public FetchAliasResponse getDetailsWithAliasOriginCountry(FetchAliasRequest inputReq)
            throws FetchAliasNoDataException, FetchAiasResponseException {
        FetchAliasResponse faresp = null;
        System.out.println("getDetailsWithAliasOriginCountry :: ");
        try {
            for (FetchResponseEntity fchRespIterator : fetchRepo.findAll()) {

                if ((fchRespIterator.getAlias().equals(inputReq.getAlias()))
                        && (fchRespIterator.getCountry().equalsIgnoreCase(inputReq.getOriginatorCountry()))) {
                    isRecordExist = true;
                    // faresp = fetchAlisRespEntyMap.mapResponseObject(fchRespIterator);
                    // break;
                    responseMapObj.put(fchRespIterator.getId(), fchRespIterator);
                }

            }
            System.out.println(" is the flag :: " + isRecordExist);
            NoDataHadle(isRecordExist);
        } catch (FetApiRequestBodyException e) {
            throw new FetchAiasResponseException(e.toString());
        }
        // System.out.println(" faresp :: " + faresp.toString());

        return fetchAlisRespEntyMap.mapResponseObject(returnlatestEntry());

    }

    public FetchAliasResponse getDetailsWithAliasDirectoryNameDirectorytypeOriginCountry(FetchAliasRequest inputReq)
            throws FetchAliasNoDataException, FetchAiasResponseException {
        FetchAliasResponse faresp = null;
        System.out.println("getDetailsWithAliasDirectoryNameDirectorytypeOriginCountry :: ");
        try {
            for (FetchResponseEntity fchRespIterator : fetchRepo.findAll()) {

                if (fchRespIterator.getAlias().equals(inputReq.getAlias())
                        && (fchRespIterator.getCountry().equalsIgnoreCase(inputReq.getOriginatorCountry())
                                && (fchRespIterator.getNetworkName().equalsIgnoreCase(inputReq.getDirectoryName()))
                                && (fchRespIterator.getNetworkType().equalsIgnoreCase(inputReq.getDirectoryType())))) {
                    isRecordExist = true;
                    // faresp = fetchAlisRespEntyMap.mapResponseObject(fchRespIterator);
                    // break;
                    responseMapObj.put(fchRespIterator.getId(), fchRespIterator);
                }

            }
            System.out.println(" is the flag :: " + isRecordExist);
            NoDataHadle(isRecordExist);
        } catch (FetApiRequestBodyException e) {
            throw new FetchAiasResponseException(e.toString());
        }
        // System.out.println(" faresp :: " + faresp.toString());

        return fetchAlisRespEntyMap.mapResponseObject(returnlatestEntry());
    }

    public FetchAliasResponse getDetailsWithAliasDirectoryNameDirectorytype(FetchAliasRequest inputReq)
            throws FetchAliasNoDataException, FetchAiasResponseException {
        FetchAliasResponse faresp = null;
        System.out.println("getDetailsWithAliasAndDirectoryNameDirectorytype :: ");
        try {
            for (FetchResponseEntity fchRespIterator : fetchRepo.findAll()) {

                if ((fchRespIterator.getAlias().equals(inputReq.getAlias()))
                        && (fchRespIterator.getNetworkName().equalsIgnoreCase(inputReq.getDirectoryName()))
                        && (fchRespIterator.getNetworkType().equalsIgnoreCase(inputReq.getDirectoryType()))) {
                    isRecordExist = true;
                    // faresp = fetchAlisRespEntyMap.mapResponseObject(fchRespIterator);
                    // break;
                    responseMapObj.put(fchRespIterator.getId(), fchRespIterator);
                }

            }
            System.out.println(" is the flag :: " + isRecordExist);
            NoDataHadle(isRecordExist);
        } catch (FetApiRequestBodyException e) {
            throw new FetchAiasResponseException(e.toString());
        }
        // System.out.println(" faresp :: " + faresp.toString());

        return fetchAlisRespEntyMap.mapResponseObject(returnlatestEntry());
    }

    public FetchAliasResponse getDetailsWithAliasAndDirectoryName(FetchAliasRequest inputReq)
            throws FetchAliasNoDataException, FetchAiasResponseException {
        FetchAliasResponse faresp = null;
        System.out.println("getDetailsWithAliasAndDirectoryName :: ");
        try {
            for (FetchResponseEntity fchRespIterator : fetchRepo.findAll()) {

                if ((fchRespIterator.getAlias().equals(inputReq.getAlias()))
                        && (fchRespIterator.getNetworkName().equalsIgnoreCase(inputReq.getDirectoryName()))) {
                    isRecordExist = true;
                    // faresp = fetchAlisRespEntyMap.mapResponseObject(fchRespIterator);
                    // break;
                    responseMapObj.put(fchRespIterator.getId(), fchRespIterator);
                }

            }
            System.out.println(" is the flag :: " + isRecordExist);
            NoDataHadle(isRecordExist);
        } catch (FetApiRequestBodyException e) {
            throw new FetchAiasResponseException(e.toString());
        }
        // System.out.println(" faresp :: " + faresp.toString());

        return fetchAlisRespEntyMap.mapResponseObject(returnlatestEntry());
    }

    public FetchAliasResponse getDetailsWithAliasAndDirectoryNameOriginCountry(FetchAliasRequest inputReq)
            throws FetchAliasNoDataException, FetchAiasResponseException {
        FetchAliasResponse faresp = null;
        System.out.println("getDetailsWithAliasAndDirectoryNameOriginCountry :: ");
        try {
            for (FetchResponseEntity fchRespIterator : fetchRepo.findAll()) {

                if ((fchRespIterator.getAlias().equals(inputReq.getAlias()))
                        && (fchRespIterator.getNetworkName().equalsIgnoreCase(inputReq.getDirectoryName()))
                        && (fchRespIterator.getCountry().equalsIgnoreCase(inputReq.getOriginatorCountry()))) {
                    isRecordExist = true;
                    // faresp = fetchAlisRespEntyMap.mapResponseObject(fchRespIterator);
                    // break;
                    responseMapObj.put(fchRespIterator.getId(), fchRespIterator);
                }

            }
            System.out.println(" is the flag :: " + isRecordExist);
            NoDataHadle(isRecordExist);
        } catch (FetApiRequestBodyException e) {
            throw new FetchAiasResponseException(e.toString());
        }
        // System.out.println(" faresp :: " + faresp.toString());

        return fetchAlisRespEntyMap.mapResponseObject(returnlatestEntry());
    }

    public void NoDataHadle(boolean flag) throws FetchAliasNoDataException {

        if (!isRecordExist) {

            throw new FetchAliasNoDataException(" No Data Matching input ");
        } else {
            isRecordExist = false;
        }
    }

    public void saveAliasData(FetchResponseEntity fchResp) {

        fetchRepo.save(fchResp);

    }

    public FetchResponseEntity returnlatestEntry() {

        return responseMapObj.get(responseMapObj.lastKey());

    }
}
