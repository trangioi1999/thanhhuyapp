package com.example.thanhhuyapp.model;

import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.logging.Handler;

public class User {
     String id;
    public String tenkhachhang;
    public String sodienthoai;
    public String diachinha;
    public String loaigas;
    public String ngaybatdau;
    public String ngaythaygas;

    public User() {
    }

    public User(String tenkhachhang, String sodienthoai, String diachinha, String loaigas, String ngaybatdau, String ngaythaygas) {
        this.tenkhachhang = tenkhachhang;
        this.sodienthoai = sodienthoai;
        this.diachinha = diachinha;
        this.loaigas = loaigas;
        this.ngaybatdau = ngaybatdau;
        this.ngaythaygas = ngaythaygas;
    }

    public User(String id, String tenkhachhang, String sodienthoai, String diachinha, String loaigas, String ngaybatdau, String ngaythaygas) {
        this.id = id;
        this.tenkhachhang = tenkhachhang;
        this.sodienthoai = sodienthoai;
        this.diachinha = diachinha;
        this.loaigas = loaigas;
        this.ngaybatdau = ngaybatdau;
        this.ngaythaygas = ngaythaygas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenkhachhang() {
        return tenkhachhang;
    }

    public void setTenkhachhang(String tenkhachhang) {
        this.tenkhachhang = tenkhachhang;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getDiachinha() {
        return diachinha;
    }

    public void setDiachinha(String diachinha) {
        this.diachinha = diachinha;
    }

    public String getLoaigas() {
        return loaigas;
    }

    public void setLoaigas(String loaigas) {
        this.loaigas = loaigas;
    }

    public String getNgaybatdau() {
        return ngaybatdau;
    }

    public void setNgaybatdau(String ngaybatdau) {
        this.ngaybatdau = ngaybatdau;
    }

    public String getNgaythaygas() {
        return ngaythaygas;
    }

    public void setNgaythaygas(String ngaythaygas) {
        this.ngaythaygas = ngaythaygas;
    }
}
