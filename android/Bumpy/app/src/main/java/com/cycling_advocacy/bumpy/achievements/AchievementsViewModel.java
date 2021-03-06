package com.cycling_advocacy.bumpy.achievements;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.cycling_advocacy.bumpy.achievements.db.AchievementEntity;
import com.cycling_advocacy.bumpy.achievements.db.AchievementRepository;

import java.util.List;

public class AchievementsViewModel extends AndroidViewModel {

    private AchievementRepository repository;
    public LiveData<List<AchievementEntity>> achievementsLiveData;

    public AchievementsViewModel(Application application) {
        super(application);

        this.repository = new AchievementRepository(application);
        this.achievementsLiveData = this.repository.getAchievements();
    }

    public void updateAll(AchievementEntity... achievement) {
        this.repository.updateAllAsync(achievement);
    }
}