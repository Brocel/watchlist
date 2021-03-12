package com.openclassroom.watchlist;

import java.util.List;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.openclassroom.watchlist.domain.WatchlistItem;
import com.openclassroom.watchlist.repository.WatchlistRepository;
import com.openclassroom.watchlist.service.WatchlistService;

@RunWith(MockitoJUnitRunner.class)
public class WatchlistServiceTest {

	// Test with dependencies injections
	@Mock
	private WatchlistRepository watchlistRepositoryMock;
	
	 @InjectMocks
	 private WatchlistService watchlistService;
	 
	 @Test
	 public void testGetWatchlistItemsReturnsAllItemsFromRepository() {
		 
		 //Arrange
		 WatchlistItem item1 = new WatchlistItem(1, "Star Wars", "8", "M", "");
		 WatchlistItem item2 = new WatchlistItem(2, "Star Treck", "7", "M", "");
		 
		 List<WatchlistItem> mockItems = Arrays.asList(item1, item2);
		 
		 Mockito.when(watchlistRepositoryMock.getList()).thenReturn(mockItems);
		 //Act
		 List<WatchlistItem> result = watchlistService.getWatchlistItems();
		 
		 //Assert
		 Assert.assertTrue(result.size() == 2);
		 Assert.assertTrue(result.get(0).getTitle().equals("Star Wars"));
		 Assert.assertTrue(result.get(1).getTitle().equals("Star Treck"));
	 }
	
}
