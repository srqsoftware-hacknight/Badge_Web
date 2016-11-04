package io.srqsoftware;

import java.util.List;

public interface BadgeService {
	String createBadge(Badge badge);
	void updateBadge(Badge badge);
	List<Badge> getAllBadges();
}
