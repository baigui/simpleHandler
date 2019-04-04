//
// Created by msi on 2019/4/4.
//

#include <syscall.h>
#include "Looper.h"
#include <cstdio>
#include <sys/eventfd.h>
#include <zconf.h>
#include <android/log.h>

Looper::Looper() {

    int pendingEventCount;
    int pendingEventIndex;
    epollFd = epoll_create(1);
    inotifyFd = eventfd(0, EFD_NONBLOCK | EFD_CLOEXEC);
//    int result_notify = inotify_add_watch(inotifyFd, argv[1], IN_CREATE | IN_DELETE);

    struct epoll_event eventItem;
    eventItem.events = EPOLLIN|EPOLLET;
    eventItem.data.fd = inotifyFd;
    epoll_ctl(epollFd, EPOLL_CTL_ADD, inotifyFd, &eventItem);

}

Looper::~Looper() {

}

void Looper::wait(int delay) {

    if (delay == 0) {
        int pollResult = epoll_wait(epollFd, pendingEventItems, 16, -1);
    } else {
        int pollResult = epoll_wait(epollFd, pendingEventItems, 16,delay);
    }

    __android_log_print(ANDROID_LOG_ERROR, "zj", "%s", "after wait");
}

void Looper::intrupt() {
    uint64_t inc = 1;
    write(inotifyFd, &inc, sizeof(uint64_t));
}