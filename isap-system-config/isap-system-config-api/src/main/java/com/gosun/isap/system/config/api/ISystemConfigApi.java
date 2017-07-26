package com.gosun.isap.system.config.api;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.gosun.isap.common.ResponseResult;


@Path("systemconfig")
public interface ISystemConfigApi {

    @Path("networkinfo")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    ResponseResult getNetworkInfo();


    @Path("cpuinfo")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    ResponseResult getCpuInfo();

    @Path("memoryinfo")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    ResponseResult getMemoryInfo();

    @Path("filesysteminfo")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    ResponseResult getFileSystemInfo();

    @Path("backupdatabase")
    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    Response getBackup();

    @Path("setnetwork")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    ResponseResult setNetwork(IpEntity ipEntity);

    @Path("backuplog")
    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    Response getLog();
}
