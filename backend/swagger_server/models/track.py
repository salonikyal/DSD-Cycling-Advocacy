# coding: utf-8

from __future__ import absolute_import
from datetime import date, datetime  # noqa: F401

from typing import List, Dict  # noqa: F401

from swagger_server.models.base_model_ import Model
from swagger_server.models.track_segments import TrackSegments  # noqa: F401,E501
from swagger_server import util


class Track(Model):
    """NOTE: This class is auto generated by the swagger code generator program.

    Do not edit the class manually.
    """
    def __init__(self, segments: List[TrackSegments]=None):  # noqa: E501
        """Track - a model defined in Swagger

        :param segments: The segments of this Track.  # noqa: E501
        :type segments: List[TrackSegments]
        """
        self.swagger_types = {
            'segments': List[TrackSegments]
        }

        self.attribute_map = {
            'segments': 'segments'
        }
        self._segments = segments

    @classmethod
    def from_dict(cls, dikt) -> 'Track':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The Track of this Track.  # noqa: E501
        :rtype: Track
        """
        return util.deserialize_model(dikt, cls)

    @property
    def segments(self) -> List[TrackSegments]:
        """Gets the segments of this Track.


        :return: The segments of this Track.
        :rtype: List[TrackSegments]
        """
        return self._segments

    @segments.setter
    def segments(self, segments: List[TrackSegments]):
        """Sets the segments of this Track.


        :param segments: The segments of this Track.
        :type segments: List[TrackSegments]
        """

        self._segments = segments
