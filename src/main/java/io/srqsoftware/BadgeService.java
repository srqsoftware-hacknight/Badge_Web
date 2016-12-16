package io.srqsoftware;

import java.util.List;

public interface BadgeService {
	String createBadge(Badge badge);
	void updateBadge(Badge badge);
	void deactivateBadge(Badge badge);
	List<Badge> getAllBadges();
	Badge getBadge(String badgeId);
}
