package com.model2.mvc.common;


public class Search {
	

	private int currentPage;
	private String searchCondition;
	private String searchKeyword;
	int pageSize;
	//==> ����Ʈȭ�� currentPage�� �ش��ϴ� ȸ�������� ROWNUM ��� SELECT ���� �߰��� Field 
		//==> UserMapper.xml �� 
		//==> <select  id="getUserList"  parameterType="search"	resultMap="userSelectMap">
		//==> ����.
	//������ ���ǿ� �´� �Ǽ��鸸 querry�� ����� ȿ������ ������ �ϱ� ����
	private int endRowNum;
	private int startRowNum;
	//�ʱ�ȭ���� ������ getter method�� ���� pagesize�� �����ؼ� ����� �� �ִ�.
	
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
		//Search Condition�� �迭 �ȿ� �־�߸� ������ �� �� �ִ� ����
		String[] conditions = {"0","1","2"};
		if(searchCondition!=null) {
			for(int i=0;i<conditions.length;i++) {
				if(searchCondition.equals(conditions[i])) {
					this.searchCondition = searchCondition;
					System.out.println("�Էµ� ��ġ ������� "+searchCondition+" �� "+conditions[i]+"���� Ȯ���߽��ϴ�======================");
				}else {
					System.out.println("�Էµ� ��ġ ������� "+searchCondition+" �� "+conditions[i]+"�� �ƴմϴ�.");
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
					System.out.println("�Էµ� ��ġ Ű������ "+searchKeyword+" �� "+conditions[i]+"�Դϴ�.");
				}
			}
		}		
	}
	//==> Select Query �� ROWNUM ������ �� 
		public int getEndRowNum() {
			return getCurrentPage()*getPageSize();
	}
		//==> Select Query �� ROWNUM ���� ��
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