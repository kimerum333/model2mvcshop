package com.model2.mvc.common;


public class Search {
	

	private int currentPage;
	private String searchCondition;
	private String searchKeyword;
	int pageSize;
	//==> 리스트화면 currentPage에 해당하는 회원정보를 ROWNUM 사용 SELECT 위해 추가된 Field 
		//==> UserMapper.xml 의 
		//==> <select  id="getUserList"  parameterType="search"	resultMap="userSelectMap">
		//==> 참조.
	//페이지 조건에 맞는 건수들만 querry로 갖고와 효율적인 쿼리를 하기 위함
	private int endRowNum;
	private int startRowNum;
	//초기화될일 없지만 getter method를 쓰면 pagesize를 가공해서 갖고올 수 있다.
	
	public Search(){
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int page) {
		this.currentPage = page;
	}

	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		//Search Condition이 배열 안에 있어야만 세팅을 할 수 있는 세터
		String[] conditions = {"0","1","2"};
		if(searchCondition!=null) {
			for(int i=0;i<conditions.length;i++) {
				if(searchCondition.equals(conditions[i])) {
					this.searchCondition = searchCondition;
					System.out.println("입력된 서치 컨디션인 "+searchCondition+" 이 "+conditions[i]+"임을 확인했습니다======================");
				}else {
					System.out.println("입력된 서치 컨디션인 "+searchCondition+" 이 "+conditions[i]+"이 아닙니다.");
				}
			}
		}
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		if(searchKeyword!=null) {
			String[] conditions = {"null","undefined"};
			for(int i=0;i<conditions.length;i++) {
				if(!searchKeyword.equals(conditions[i])) {
					this.searchKeyword = searchKeyword;
				}else {
					System.out.println("입력된 서치 키워드인 "+searchKeyword+" 이 "+conditions[i]+"입니다.");
				}
			}
		}		
	}
	//==> Select Query 시 ROWNUM 마지막 값 
		public int getEndRowNum() {
			return getCurrentPage()*getPageSize();
	}
		//==> Select Query 시 ROWNUM 시작 값
		public int getStartRowNum() {
			return (getCurrentPage()-1)*getPageSize()+1;
	}

		@Override
		public String toString() {
			return "Search [currentPage=" + currentPage + ", searchCondition="
					+ searchCondition + ", searchKeyword=" + searchKeyword
					+ ", pageSize=" + pageSize + ", endRowNum=" + endRowNum
					+ ", startRowNum=" + startRowNum + "]";
	}
}