////////////////////////////////////////////////////////////////////////////////
//
//  THIS SOFTWARE IS PROVIDED BY COSTAIN INTEGRATED TECHNOLOGY SOLUTIONS
//  LIMITED ``AS IS'', WITH NO WARRANTY, TERM OR CONDITION OF ANY KIND,
//  EXPRESS OR IMPLIED, AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING,
//  BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
//  FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL COSTAIN
//  INTEGRATED TECHNOLOGY SOLUTIONS LIMITED BE LIABLE FOR ANY LOSSES, CLAIMS
//  OR DAMAGES OF WHATEVER NATURE, INCLUDING ANY DIRECT, INDIRECT, INCIDENTAL,
//  SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES EVEN IF ADVISED OF THE
//  POSSIBILITY OF SUCH DAMAGES OR LOSSES (INCLUDING, BUT NOT LIMITED TO,
//  PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
//  OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
//  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
//  OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE.
//
//  Copyright 2016 (C) Costain Integrated Technology Solutions Limited.
//  All Rights Reserved.
//
////////////////////////////////////////////////////////////////////////////////
package com.assignment;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mohamed
 */
public class Report {

    List<Team> teams = new ArrayList<>();

    public Report(List<Team> teams) {
        this.teams = teams;
    }

    public void printTeamsSchedule() {
        for (int i = 0; i < teams.size(); i++) {
            System.out.println("Team#:" + (i + 1));
            displayTeamActivity(teams.get(i));
        }
    }

    private void displayTeamActivity(Team team) {
        for (Schedule schedule : team.getSchedule()) {
            System.out.println(schedule.getStartTime() + " " + schedule.getActivity().getActivity());
        }
    }
}
