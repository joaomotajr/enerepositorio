package br.com.eneeyes.main.result;

public abstract class BasicGroupResult<T> extends BasicResult<T> {	
			
    private boolean firstPage = false;
    private boolean lastPage = false;
    private int totalList = 0;
    private int totalPage = 0;
    private int countPages = 0;	

	public final boolean isFirstPage() {
		return firstPage;
	}

	public final void setFirstPage(boolean firstPage) {
		this.firstPage = firstPage;
	}

	public final boolean isLastPage() {
		return lastPage;
	}

	public final void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}

	public final int getTotalList() {
		return totalList;
	}

	public final void setTotalList(int totalList) {
		this.totalList = totalList;
	}

	public final int getTotalPage() {
		return totalPage;
	}

	public final void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public final int getCountPages() {
		return countPages;
	}

	public final void setCountPages(int countPages) {
		this.countPages = countPages;
	}	
}
