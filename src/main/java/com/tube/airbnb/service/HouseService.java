package com.tube.airbnb.service;

import com.tube.airbnb.config.BaseResponseStatus;
import com.tube.airbnb.dto.GetHouseInfoRes;
import com.tube.airbnb.dto.PatchHouseReq;
import com.tube.airbnb.dto.PostHouseReq;
import com.tube.airbnb.dto.PostHouseRes;
import com.tube.airbnb.entity.Host;
import com.tube.airbnb.entity.House;
import com.tube.airbnb.entity.embedded.BasicHouseProperty;
import com.tube.airbnb.exception.BaseException;
import com.tube.airbnb.repository.HouseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class HouseService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    HouseRepository houseRepository;

    HostService hostService;

    @Autowired
    public HouseService(HouseRepository houseRepository, HostService hostService) {
        this.houseRepository = houseRepository;
        this.hostService = hostService;
    }

    public PostHouseRes createHouse(PostHouseReq postHouseReq) throws BaseException {
        long hostId = postHouseReq.getHostId();
        BasicHouseProperty basicHouseProperty = postHouseReq.getBasicHouseProperty();
        hostService.validateHostJwtTokenByHostId(hostId);
        
        House house = new House();
        house.setHost(hostService.getHost(hostId));
        house.setBasicHouseProperty(basicHouseProperty);
        houseRepository.save(house);
        return PostHouseRes.from(house);
    }

    public void deleteHouse(long houseId) throws BaseException {
        House house = findHouseById(houseId);
        Host host = house.getHost();
        hostService.validateHostJwtTokenByHostId(host.getId());
        house.setStatus(1);
        houseRepository.save(house);
    }

    public void updateHouse(long houseId, PatchHouseReq patchHouseReq) throws BaseException {
        House house = findHouseById(houseId);
        Host host = house.getHost();
        hostService.validateHostJwtTokenByHostId(host.getId());
        house.setBasicHouseProperty(patchHouseReq.getBasicHouseProperty());
        houseRepository.save(house);
    }

    public House findHouseById(long houseId) throws BaseException {
        return houseRepository.findById(houseId)
                .orElseThrow(()-> new BaseException(BaseResponseStatus.HOUSES_EMPTY_HOUSE_ID));
    }

    public GetHouseInfoRes getHouseInfoById(long id) throws BaseException {
//        House house = findHouseById(id);
        House house = getHouseInfoByIdUsingFetchJoin(id);
        return GetHouseInfoRes.from(house);
    }

    public Page<GetHouseInfoRes> findHousesByPageable(Pageable pageable) throws BaseException {
        Page<House> page = houseRepository.findAll(pageable);
        return page.map(GetHouseInfoRes::from);
    }

    public House getHouseInfoByIdUsingFetchJoin(long id) throws BaseException{
        House house = houseRepository.findWithHouseFacilitiesAndFacilityById(id);
        return house;
    }
}
