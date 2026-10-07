# Domain

This document describes the application's business domain.
It serves as a shared source of truth for the team and AI tools.
It is not technical documentation.

## What is Podroosh app?

The Podroosh app is designed to assist in organizing expeditions, excursions, and trips, specifically regarding gear and expenses.
It supports planning for individuals or families as well as for entire groups of travelers.
It helps coordinate the planning of gear, provisions, and expenses for a specific trip or excursion.
It is also designed to track costs and expenses incurred by individual travelers and facilitate expense settlement within the group.
Ultimately, it includes the capability to send notifications to expedition members in specific situations.

## Problem

- what equipment is needed for the trip?
- what expenses will be required?
- who have what for the trip?
- how many people will go?
- who paid what during the trip?
- who owes whom, and how much, for travel expenses?
- how are we getting there?

## Actors

- organizer
- participant
- admin

## MVP

- Create/register user
- Create a trip
- Create/Add/remove/invite participants to/from trip
- Add/Change member role
- Create list of equipments needed and optional for trip
- Participant can add equipment to trip
- The participant may select the equipment they can supply.
- Participant can leave the trip
- Create Expense  to trip
- Add payment to Expense in trip
- Can create task for trip

## Use Cases

- Create Trip
- Add/invite participant to trip
- Add the required/optional equipment for the trip
- Participant can add equipment he has to the trip
- Remove participant from the trip
- Participant can leave the trip
- Add expenses to the trip
- Get list of trips for user
- Cancel trip
- Send a notification after trip update to participants
- Add expenses
- Calculate expenses
- Calculate expenses among the participants and show result
- Create tasks for trip
- Assign tasks to trip participants
- When user leaving Trip all his Equipments should be removed from trip
- user cannot leave Trip if trip is in progress

## Business Rules

### Trip

* **PD-TRIP-01:** Every trip must have at least one owner.
* **PD-TRIP-02:** Only the trip owners can manage the trip itself.
* **PD-TRIP-03:** A user can join a trip only through an invitation.
* **PD-TRIP-04:** The creator of the trip is automatically a owner of the trip.
* **PD-TRIP-05:** A user can be a member of a trip only once.
* **PD-TRIP-06:** A trip has a defined lifecycle represented by a status.
* **PD-TRIP-07:** A trip can change its status only according to the defined workflow.
* **PD-TRIP-08:** Only the trip owners can change the trip status.
* **PD-TRIP-09:** The owners can delete a trip only when the trip has no members other than the owner  and contains no dependent planning data.
* **PD-TRIP-10:** Deleting a trip is not allowed once other users have joined it.
* **PD-TRIP-11:** Canceling a trip is not allowed when trip is ended and expenses are not settled.
* **PD-TRIP-12:** An owners cannot leave or lose the OWNER role if this would leave the trip without an owner.

### Trip Membership

* **PD-MEMBER-01:** Membership is created only by accepting a valid invitation.
* **PD-MEMBER-02:** An invitation can be accepted only by the user it was issued to.
* **PD-MEMBER-03:** A user cannot accept an invitation if they are already a member of the trip.
* **PD-MEMBER-04:** Only the trip owners can invite users to the trip.
* **PD-MEMBER-05:** Only the trip owners can remove a member from the trip.
* **PD-MEMBER-06:** A member with the OWNER role cannot be removed from the trip by another member, including another owner.
* **PD-MEMBER-07:** Removing a member must not leave invalid references to that member in trip-related data.
* **PD-MEMBER-08:** Member can leave a trip, all member equipment should also be removed.
* **PD-MEMBER-09:** Member can not leave a trip if trip is ended and/or expenses are not settled.
* **PD-MEMBER-10:** Member can not leave a trip if trip is in progress.
* **PD-MEMBER-11:** The owners of the trip may not be deprived of their right of ownership. The owners may, however, renounce their right of ownership, provided that there is another owner.

### Trip Roles

* **PD-ROLE-01:** A trip member may be assigned the role of organizer.
* **PD-ROLE-02:** Only the trip owners can assign or revoke the organizer role.
* **PD-ROLE-03:** Organizers have permissions limited to the responsibilities explicitly assigned to the organizer role.
* **PD-ROLE-04:** Being an organizer does not transfer ownership of the trip.

### Tasks

* **PD-TASK-01:** Every task belongs to exactly one trip.
* **PD-TASK-02:** Only the trip owners and designated organizers can create tasks.
* **PD-TASK-03:** Only the trip owners and designated organizers can edit tasks.
* **PD-TASK-04:** A task may be assigned only to a member of the trip.
* **PD-TASK-05:** A task cannot be assigned to a user who is not a member of the trip.
* **PD-TASK-06:** Task status changes must follow the task workflow.
* **PD-TASK-07:** The trip owners retains task-management permissions regardless of organizer assignments.

### Invitations

* **PD-INVITE-01:** An invitation belongs to exactly one trip and one invited user.
* **PD-INVITE-02:** An invitation has a lifecycle, e.g. `PENDING`, `ACCEPTED`, `DECLINED`, `EXPIRED`.
* **PD-INVITE-03:** An invitation can be accepted only while it is valid and pending.
* **PD-INVITE-04:** Accepting an invitation creates trip membership.
* **PD-INVITE-05:** Accepting or declining an invitation makes the invitation no longer pending.
* **PD-INVITE-06:** Only the trip owners can create invitations.

## 7. Ograniczenia i założenia

- Users can join a trip only through an invitation.
- Expense settlement results are calculated by the application and are not persisted.
- A trip member can provide equipment for the trip.
- Notifications are outside the initial MVP implementation.