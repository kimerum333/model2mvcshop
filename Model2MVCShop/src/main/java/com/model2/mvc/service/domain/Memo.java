package com.model2.mvc.service.domain;

public class Memo {
	  private int memoId;
	  private String memoTitle;
	  private String memoContents;
	  private String memoOwner;

	  public Memo(int memoid, String memotitle, String memocontents, String memoowner) {
	    this.memoId = memoid;
	    this.memoTitle = memotitle;
	    this.memoContents = memocontents;
	    this.memoOwner = memoowner;
	  }

	  public int getMemoid() {
	    return memoId;
	  }

	  public void setMemoid(int memoid) {
	    this.memoId = memoid;
	  }

	  public String getMemotitle() {
	    return memoTitle;
	  }

	  public void setMemotitle(String memotitle) {
	    this.memoTitle = memotitle;
	  }

	  public String getMemocontents() {
	    return memoContents;
	  }

	  public void setMemocontents(String memocontents) {
	    this.memoContents = memocontents;
	  }

	  public String getMemoowner() {
	    return memoOwner;
	  }

	  public void setMemoowner(String memoowner) {
	    this.memoOwner = memoowner;
	  }
	}
