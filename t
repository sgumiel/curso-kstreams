[1;36mdiff --git a/mono-hilo/event-producer/src/main/java/com/gumi/cursos/kstream/monohilo/eventproducer/event/Event.java b/mono-hilo/event-producer/src/main/java/com/gumi/cursos/kstream/monohilo/eventproducer/event/Event.java[m
[1;36mindex ea6c1ae..c31a857 100644[m
[1;36m--- a/mono-hilo/event-producer/src/main/java/com/gumi/cursos/kstream/monohilo/eventproducer/event/Event.java[m
[1;36m+++ b/mono-hilo/event-producer/src/main/java/com/gumi/cursos/kstream/monohilo/eventproducer/event/Event.java[m
[1;34m@@ -1,2 +1,18 @@[m
[1;31m-package com.gumi.cursos.kstream.monohilo.eventproducer.event;public class Event {[m
[1;32m+[m[1;32mpackage com.gumi.cursos.kstream.monohilo.eventproducer.event;[m
[1;32m+[m
[1;32m+[m[1;32mimport java.io.Serializable;[m
[1;32m+[m
[1;32m+[m[1;32mimport lombok.AllArgsConstructor;[m
[1;32m+[m[1;32mimport lombok.Builder;[m
[1;32m+[m[1;32mimport lombok.Data;[m
[1;32m+[m[1;32mimport lombok.NoArgsConstructor;[m
[1;32m+[m
[1;32m+[m[1;32m@Data[m
[1;32m+[m[1;32m@AllArgsConstructor[m
[1;32m+[m[1;32m@NoArgsConstructor[m
[1;32m+[m[1;32m@Builder[m
[1;32m+[m[1;32mpublic class Event implements Serializable {[m
[1;32m+[m
[1;32m+[m	[1;32mprivate int milisToSleep;[m
[1;32m+[m	[1;32mprivate int number;[m
 }[m
[1;36mdiff --git a/mono-hilo/event-producer/src/main/java/com/gumi/cursos/kstream/monohilo/eventproducer/event/rest/EventController.java b/mono-hilo/event-producer/src/main/java/com/gumi/cursos/kstream/monohilo/eventproducer/event/rest/EventController.java[m
[1;36mindex b969b18..0b27d87 100644[m
[1;36m--- a/mono-hilo/event-producer/src/main/java/com/gumi/cursos/kstream/monohilo/eventproducer/event/rest/EventController.java[m
[1;36m+++ b/mono-hilo/event-producer/src/main/java/com/gumi/cursos/kstream/monohilo/eventproducer/event/rest/EventController.java[m
[1;34m@@ -1,2 +1,38 @@[m
[1;31m-package com.gumi.cursos.kstream.monohilo.eventproducer.rest;public class EventController {[m
[1;32m+[m[1;32mpackage com.gumi.cursos.kstream.monohilo.eventproducer.event.rest;[m
[1;32m+[m
[1;32m+[m[1;32mimport java.util.List;[m
[1;32m+[m[1;32mimport java.util.stream.Collectors;[m
[1;32m+[m[1;32mimport java.util.stream.IntStream;[m
[1;32m+[m
[1;32m+[m[1;32mimport com.gumi.cursos.kstream.monohilo.eventproducer.event.Event;[m
[1;32m+[m[1;32mimport com.gumi.cursos.kstream.monohilo.eventproducer.event.rest.request.CreateEventsRequest;[m
[1;32m+[m[1;32mimport org.springframework.web.bind.annotation.PostMapping;[m
[1;32m+[m[1;32mimport org.springframework.web.bind.annotation.RequestBody;[m
[1;32m+[m[1;32mimport org.springframework.web.bind.annotation.RequestMapping;[m
[1;32m+[m[1;32mimport org.springframework.web.bind.annotation.RestController;[m
[1;32m+[m
[1;32m+[m[1;32m@RestController[m
[1;32m+[m[1;32m@RequestMapping("event")[m
[1;32m+[m[1;32mpublic class EventController {[m
[1;32m+[m
[1;32m+[m	[1;32m@PostMapping[m
[1;32m+[m	[1;32mpublic List<Event> createEvents(@RequestBody final CreateEventsRequest createEventsRequest) {[m
[1;32m+[m
[1;32m+[m		[1;32mfinal List<Event> events = IntStream.range(0, createEventsRequest.getNumberOfEvents())[m
[1;32m+[m				[1;32m.mapToObj( i -> this.builder(createEventsRequest.getMin(), createEventsRequest.getMax()))[m
[1;32m+[m				[1;32m.collect(Collectors.toList());[m
[1;32m+[m
[1;32m+[m		[1;32mreturn events;[m
[1;32m+[m
[1;32m+[m	[1;32m}[m
[1;32m+[m
[1;32m+[m	[1;32mprivate Event builder(final int min, final int max) {[m
[1;32m+[m
[1;32m+[m		[1;32mfinal int milisToSleep = (int)(Math.random()*(3000-1000)+1000);[m
[1;32m+[m		[1;32mfinal int number = (int)(Math.random()*(max-min+1)+min);[m
[1;32m+[m		[1;32mreturn  Event.builder()[m
[1;32m+[m				[1;32m.milisToSleep(milisToSleep)[m
[1;32m+[m				[1;32m.number(number).build();[m
[1;32m+[m
[1;32m+[m	[1;32m}[m
 }[m
[1;36mdiff --git a/mono-hilo/event-producer/src/main/java/com/gumi/cursos/kstream/monohilo/eventproducer/event/rest/request/CreateEventsRequest.java b/mono-hilo/event-producer/src/main/java/com/gumi/cursos/kstream/monohilo/eventproducer/event/rest/request/CreateEventsRequest.java[m
[1;36mindex 4cedc8c..6d8032b 100644[m
[1;36m--- a/mono-hilo/event-producer/src/main/java/com/gumi/cursos/kstream/monohilo/eventproducer/event/rest/request/CreateEventsRequest.java[m
[1;36m+++ b/mono-hilo/event-producer/src/main/java/com/gumi/cursos/kstream/monohilo/eventproducer/event/rest/request/CreateEventsRequest.java[m
[1;34m@@ -1,2 +1,19 @@[m
[1;31m-package com.gumi.cursos.kstream.monohilo.eventproducer.event.rest.request;public class CreateEventsRequest {[m
[1;32m+[m[1;32mpackage com.gumi.cursos.kstream.monohilo.eventproducer.event.rest.request;[m
[1;32m+[m
[1;32m+[m[1;32mimport java.io.Serializable;[m
[1;32m+[m
[1;32m+[m[1;32mimport lombok.AllArgsConstructor;[m
[1;32m+[m[1;32mimport lombok.Builder;[m
[1;32m+[m[1;32mimport lombok.Data;[m
[1;32m+[m[1;32mimport lombok.NoArgsConstructor;[m
[1;32m+[m
[1;32m+[m[1;32m@Data[m
[1;32m+[m[1;32m@Builder[m
[1;32m+[m[1;32m@NoArgsConstructor[m
[1;32m+[m[1;32m@AllArgsConstructor[m
[1;32m+[m[1;32mpublic class CreateEventsRequest implements Serializable {[m
[1;32m+[m
[1;32m+[m	[1;32mprivate int numberOfEvents;[m
[1;32m+[m	[1;32mprivate int max;[m
[1;32m+[m	[1;32mprivate int min;[m
 }[m
