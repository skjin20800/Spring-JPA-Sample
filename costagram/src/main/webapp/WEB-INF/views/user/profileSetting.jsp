<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
    <%@ include file="../layout/header.jsp"%>
    
    <!--프로필셋팅 메인-->
    <main class="main">
        <!--프로필셋팅 섹션-->
        <section class="setting-container">
            <!--프로필셋팅 아티클-->
            <article class="setting__content">

                <!--프로필셋팅 아이디영역-->
                <div class="content-item__01">
                    <div class="item__img"><img src="/images/profile.jpeg" alt=""></div>
                    <div class="item__username">
                        <h2>${user.username}</h2>
                    </div>
                </div>
                <!--프로필셋팅 아이디영역end-->
                
                <!--프로필 수정-->
                <form>
                    <div class="content-item__02">
                        <div class="item__title">이름</div>
                        <div class="item__input">
                            <input type="text" id="username" name="username" value="${user.username}" />
                        </div>
                    </div>
                    <div class="content-item__03">
                        <div class="item__title">사용자 이름</div>
                        <div class="item__input">
                            <input type="text" id="name" name="name" value="${user.name}" />
                        </div>
                    </div>
                    <div class="content-item__04">
                        <div class="item__title">웹사이트</div>
                        <div class="item__input">
                            <input type="text" id="website" name="website" value="${user.website}" />
                        </div>
                    </div>
                    <div class="content-item__05">
                        <div class="item__title">소개</div>
                        <div class="item__input">
                            <textarea name="bio" id="bio" rows="3">${user.bio}</textarea>
                        </div>
                    </div>
                    <div class="content-item__06">
                        <div class="item__title"></div>
                        <div class="item__input">
                            <span><b>개인정보</b></span>
                            <span>비즈니스나 반려동물 등에 사용된 계정인 경우에도 회원님의 개인 정보를 입력하세요. 공개 프로필에는 포함되지 않습니다.</span>
                        </div>
                    </div>
                    <div class="content-item__07">
                        <div class="item__title">이메일</div>
                        <div class="item__input">
                            <input type="text" id="email" name="email" value="${user.email}" />
                        </div>
                    </div>
                    <div class="content-item__08">
                        <div class="item__title">전회번호</div>
                        <div class="item__input">
                            <input type="text" id="phone" name="phone" value="${user.phone}" />
                        </div>
                    </div>
                    <div class="content-item__09">
                        <div class="item__title">성별</div>
                        <div class="item__input">
                            <input type="text" id="gender" name="gender"  value= "${user.gender}"/>
                        </div>
                    </div>
                    
                    <!--제출버튼-->
                    <div class="content-item__11">
                        <div class="item__title"></div>
                        <div class="item__input">
                            <button id="profileUpdate">제출</button>
                        </div>
                    </div>
                    <!--제출버튼end-->
                    
                </form>
                <!--프로필수정 form end-->
            </article>
        </section>
    </main>
    <script src="/js/profileSetting.js"></script>
    
    <%@ include file="../layout/footer.jsp" %>