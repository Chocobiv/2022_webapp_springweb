package com.Ezenweb.domain.dao;

import com.Ezenweb.domain.dto.BoardDto;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

//@Repository
public class BoardDao {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public BoardDao(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/springweb","root","1234");
        }catch (Exception e) {  System.out.println("DB 연동 실패"); }
    }

    //1. 게시물 등록 SQL
    public boolean setboard(BoardDto boardDto){
        String sql = "insert into board(btitle,bcontent) values (?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, boardDto.getBtitle());
            ps.setString(2, boardDto.getBcontent());
            ps.executeUpdate();
            return true;
        }catch(Exception e){ System.out.println(e); }
        return false;
    }

    //2. 게시물 목록 SQL
    public ArrayList<BoardDto> getboards(){
        ArrayList<BoardDto> boards = new ArrayList<>();
        String sql = "select * from board";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) {
                BoardDto boardDto = new BoardDto();
                boardDto.setBno(rs.getInt(1));
                boardDto.setBtitle(rs.getString(2));
                boardDto.setBcontent(rs.getString(3));
                boards.add(boardDto);
            }
        }catch(Exception e){ System.out.println(e); }
        return boards;
    }

    //3. 게시물 개별 조회 SQL
    public BoardDto getboard(int bno){
        BoardDto dto = new BoardDto();
        String sql = "select * from board where bno="+bno;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) {

            }
        }catch(Exception e){ System.out.println(e); }
        return dto;
    }
}
