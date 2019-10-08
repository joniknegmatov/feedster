package io.jonibek.feedster.usecase

import io.jonibek.feedster.data.repository.post.PostRepository
import io.jonibek.feedster.domain.feeditem.FeedItemUseCase
import io.mockk.mockk
import io.reactivex.Scheduler
import org.junit.Before

class FeedsItemUseCaseTest {

    private lateinit var testSubject : FeedItemUseCase.FeedItemUseCaseImpl
    private lateinit var mockSchedulerIO: Scheduler
    private lateinit var mockSchedulerMain: Scheduler
    private lateinit var mockPostRepository: PostRepository

    @Before
    fun init(){
        mockPostRepository = mockk(relaxed = true)
        mockSchedulerIO = mockk()
        mockSchedulerMain = mockk()
        testSubject = FeedItemUseCase.FeedItemUseCaseImpl(mockPostRepository, mockSchedulerIO,mockSchedulerMain)
    }
}