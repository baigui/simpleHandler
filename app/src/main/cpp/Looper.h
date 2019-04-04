//
// Created by msi on 2019/4/4.
//

#ifndef SIMPLEHANDLER2_LOOPER_H
#define SIMPLEHANDLER2_LOOPER_H
#include <sys/epoll.h>

class Looper {
public:
    Looper();
    ~Looper();
    void wait(int delay);
    void intrupt();

private:
    int mEpollFd;
    int epollFd;
    int inotifyFd;

    struct epoll_event pendingEventItems[16];
};

#endif //SIMPLEHANDLER2_LOOPER_H
