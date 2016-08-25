package com.hatcher.app.util;

public class LoginConfig extends BasicConfig{
	
	private static LoginConfig instance = new LoginConfig();
	
	private String accessToken;
	private String memberId;
	private String memberType;
	private String nickName;
	private String mobliePhone;
	private String wxOpenId;
	private String qqOpenId;
	private boolean isHasTuya = true;
	private boolean isHasHelpNotes = true;
	private boolean isLogout = false;
	private String location;
	private String typeFilter;
	private String imToken;
	private String headImage;
	private String activityLocation;
	private String deviceid;
	private String ncoins;
	private long openAppDate;
	private int greatNum;
	private int badNum;
	private boolean soundIsOpen = true;
	private boolean noticeIsOpen = true;
	private boolean isOpen = false;
	private boolean isDashOpen = false;
	private boolean isWelcomeToGuide = true;
	private int curShareNum;
	private int missionShareNum;
	private long lastShareDate;
	private long lastSignDate;
	private boolean isSigned = false;

	private boolean isAutoStart = true;
	private boolean isLongBright = true;
	private boolean isNightMode = false;
	private boolean isTripStart = false;

	private boolean isListEmpty = true;

	public static LoginConfig getInstance() {
		return instance;
	}
	
	
	protected void init() {
		accessToken = super.getSp().getString("accessToken", "");
		memberId = super.getSp().getString("memberId", "");
		memberType = super.getSp().getString("memberType", "");
		nickName = super.getSp().getString("nickName", "");
		mobliePhone = super.getSp().getString("mobliePhone", "");
		isHasTuya = super.getSp().getBoolean("isHasTuya", true);
		isHasHelpNotes = super.getSp().getBoolean("isHasHelpNotes", true);
		isLogout = super.getSp().getBoolean("isLogout", false);
		location = super.getSp().getString("location", "");
		typeFilter = super.getSp().getString("typeFilter", "");
		imToken = super.getSp().getString("imToken", "");
		headImage = super.getSp().getString("headImage", "");
		deviceid = super.getSp().getString("deviceid", "");
		openAppDate = super.getSp().getLong("openAppDate", 0);
		isOpen = super.getSp().getBoolean("isOpen", false);
		isDashOpen = super.getSp().getBoolean("isDashOpen", false);
		isWelcomeToGuide = super.getSp().getBoolean("isWelcomeToGuide", true);
		greatNum = super.getSp().getInt("greatNum", 0);
		badNum = super.getSp().getInt("badNum", 0);
		soundIsOpen = super.getSp().getBoolean("soundIsOpen", true);
		noticeIsOpen = super.getSp().getBoolean("noticeIsOpen", true);
		wxOpenId = super.getSp().getString("wxOpenId", "");
		qqOpenId = super.getSp().getString("qqOpenId", "");
		ncoins = super.getSp().getString("ncoins", "0");

		activityLocation = super.getSp().getString("activityLocation", "");
		curShareNum = super.getSp().getInt("curShareNum", 0);
		missionShareNum = super.getSp().getInt("missionShareNum", 2);
		lastShareDate = super.getSp().getLong("lastShareDate", 0);
		lastSignDate = super.getSp().getLong("lastSignDate", 0);
		isSigned = super.getSp().getBoolean("isSigned", false);
		isAutoStart = super.getSp().getBoolean("isAutoStart", true);
		isLongBright = super.getSp().getBoolean("isLongBright", true);
		isNightMode = super.getSp().getBoolean("isNightMode", false);
		isTripStart = super.getSp().getBoolean("isTripStart", false);
		isListEmpty = super.getSp().getBoolean("isListEmpty", true);

	}
	
	public String getAccessToken() {
		System.out.println(accessToken);
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
		super.prepare().putString("accessToken", accessToken).commit();
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
		super.prepare().putString("memberId", memberId).commit();
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
		super.prepare().putString("memberType", memberType).commit();
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
		super.prepare().putString("nickName", nickName).commit();
	}

	public boolean getHasTuya() {
		return isHasTuya;
	}

	public void setHasTuya(boolean isHasTuya) {
		this.isHasTuya = isHasTuya;
		super.prepare().putBoolean("isHasTuya", isHasTuya).commit();
	}
	
	public boolean getHasHelpNotes() {
		return isHasHelpNotes;
	}

	public void setHasHelpNotes(boolean isHasHelpNotes) {
		this.isHasHelpNotes = isHasHelpNotes;
		super.prepare().putBoolean("isHasHelpNotes", isHasHelpNotes).commit();
	}

	public boolean getLogout() {
		return isLogout;
	}

	public void setLogout(boolean isLogout) {
		this.isLogout = isLogout;
		super.prepare().putBoolean("isLogout", isLogout).commit();
	}

	public String getMobliePhone() {
		return mobliePhone;
	}

	public void setMobliePhone(String mobliePhone) {
		this.mobliePhone = mobliePhone;
		super.prepare().putString("mobliePhone", mobliePhone).commit();
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
		super.prepare().putString("location", location).commit();
	}

	public String getTypeFilter() {
		return typeFilter;
	}

	public void setTypeFilter(String typeFilter) {
		this.typeFilter = typeFilter;
		super.prepare().putString("typeFilter", typeFilter).commit();
	}

	public String getImToken() {
		return imToken;
	}

	public void setImToken(String imToken) {
		this.imToken = imToken;
		super.prepare().putString("imToken", imToken).commit();
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
		super.prepare().putString("headImage", headImage).commit();
	}

	public long getOpenAppDate() {
		return openAppDate;
	}

	public void setOpenAppDate(long openAppDate) {
		this.openAppDate = openAppDate;
		super.prepare().putLong("openAppDate", openAppDate).commit();
	}

	public boolean isOpen() {
		return isOpen;
	}

	public String getDeviceid() {
		return deviceid;
	}


	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
		super.prepare().putString("deviceid", deviceid).commit();
	}


	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
		super.prepare().putBoolean("isOpen", isOpen).commit();
	}

	public String getActivityLocation() {
		return activityLocation;
	}

	public void setActivityLocation(String activityLocation) {
		this.activityLocation = activityLocation;
		super.prepare().putString("activityLocation", activityLocation).commit();
	}

	public String getWxOpenId() {
		return wxOpenId;
	}

	public void setWxOpenId(String wxOpenId) {
		this.wxOpenId = wxOpenId;
		super.prepare().putString("wxOpenId", wxOpenId).commit();
	}

	public String getQqOpenId() {
		return qqOpenId;
	}

	public void setQqOpenId(String qqOpenId) {
		this.qqOpenId = qqOpenId;
		super.prepare().putString("qqOpenId", qqOpenId).commit();
	}

	public int getGreatNum() {
		return greatNum;
	}

	public void setGreatNum(int greatNum) {
		this.greatNum = greatNum;
		super.prepare().putInt("greatNum", greatNum).commit();
	}

	public int getBadNum() {
		return badNum;
	}

	public void setBadNum(int badNum) {
		this.badNum = badNum;
		super.prepare().putInt("badNum", badNum).commit();
	}

	public boolean getSoundIsOpen() {
		return soundIsOpen;
	}
	public void setSoundIsOpen(boolean soundIsOpen) {
		this.soundIsOpen = soundIsOpen;
		super.prepare().putBoolean("soundIsOpen", soundIsOpen).commit();
	}
	public boolean getNoticeIsOpen() {
		return noticeIsOpen;
	}
	public void setNoticeIsOpen(boolean noticeIsOpen) {
		this.noticeIsOpen = noticeIsOpen;
		super.prepare().putBoolean("noticeIsOpen", noticeIsOpen).commit();
	}

	public boolean getDashOpen() {
		return isDashOpen;
	}

	public void setDashOpen(boolean isDashOpen) {
		this.isDashOpen = isDashOpen;
		super.prepare().putBoolean("isDashOpen", isDashOpen).commit();
	}

	public boolean getWelcomeToGuide() {
		return isWelcomeToGuide;
	}

	public void setWelcomeToGuide(boolean welcomeToGuide) {
		isWelcomeToGuide = welcomeToGuide;
		super.prepare().putBoolean("isWelcomeToGuide", isWelcomeToGuide).commit();
	}

	public String getNcoins() {
		return ncoins;
	}

	public void setNcoins(String ncoins) {
		this.ncoins = ncoins;
		super.prepare().putString("ncoins", ncoins).commit();
	}

	public int getCurShareNum() {
		return curShareNum;
	}

	public void setCurShareNum(int curShareNum) {
		this.curShareNum = curShareNum;
		super.prepare().putInt("curShareNum", curShareNum).commit();
}

	public int getMissionShareNum() {
		return missionShareNum;
	}

	public void setMissionShareNum(int missionShareNum) {
		this.missionShareNum = missionShareNum;
		super.prepare().putInt("missionShareNum", missionShareNum).commit();
	}

	public long getLastShareDate() {
		return lastShareDate;
	}

	public void setLastShareDate(long lastShareDate) {
		this.lastShareDate = lastShareDate;
		super.prepare().putLong("lastShareDate", lastShareDate).commit();
	}

	public long getLastSignDate() {
		return lastSignDate;
	}

	public void setLastSignDate(long lastSignDate) {
		this.lastSignDate = lastSignDate;
		super.prepare().putLong("lastSignDate", lastSignDate).commit();
	}

	public boolean isSigned() {
		return isSigned;
	}

	public void setSigned(boolean signed) {
		isSigned = signed;
		super.prepare().putBoolean("isSigned", isSigned).commit();
	}

	public boolean isAutoStart() {
		return isAutoStart;
	}

	public void setAutoStart(boolean autoStart) {
		isAutoStart = autoStart;
		super.prepare().putBoolean("isAutoStart", isAutoStart).commit();
	}

	public boolean isLongBright() {
		return isLongBright;
	}

	public void setLongBright(boolean longBright) {
		isLongBright = longBright;
		super.prepare().putBoolean("isLongBright", isLongBright).commit();
	}

	public boolean isNightMode() {
		return isNightMode;
	}

	public void setNightMode(boolean nightMode) {
		isNightMode = nightMode;
		super.prepare().putBoolean("isNightMode", isNightMode).commit();
	}

	public boolean isTripStart() {
		return isTripStart;
	}

	public void setTripStart(boolean tripStart) {
		isTripStart = tripStart;
		super.prepare().putBoolean("isTripStart", isTripStart).commit();
	}

	public boolean isListEmpty() {
		return isListEmpty;
	}

	public void setListEmpty(boolean listEmpty) {
		isListEmpty = listEmpty;
		super.prepare().putBoolean("isListEmpty", isListEmpty).commit();
	}
}
