# coding: utf-8

from __future__ import absolute_import
from datetime import date, datetime  # noqa: F401

from typing import List, Dict  # noqa: F401

from swagger_server.models.base_model_ import Model
from swagger_server import util


class ProcessedTripVibration(Model):
    """NOTE: This class is auto generated by the swagger code generator program.

    Do not edit the class manually.
    """
    def __init__(self, max_vibration: float=None, min_vibration: float=None, avg_vibration: float=None):  # noqa: E501
        """ProcessedTripVibration - a model defined in Swagger

        :param max_vibration: The max_vibration of this ProcessedTripVibration.  # noqa: E501
        :type max_vibration: float
        :param min_vibration: The min_vibration of this ProcessedTripVibration.  # noqa: E501
        :type min_vibration: float
        :param avg_vibration: The avg_vibration of this ProcessedTripVibration.  # noqa: E501
        :type avg_vibration: float
        """
        self.swagger_types = {
            'max_vibration': float,
            'min_vibration': float,
            'avg_vibration': float
        }

        self.attribute_map = {
            'max_vibration': 'maxVibration',
            'min_vibration': 'minVibration',
            'avg_vibration': 'avgVibration'
        }
        self._max_vibration = max_vibration
        self._min_vibration = min_vibration
        self._avg_vibration = avg_vibration

    @classmethod
    def from_dict(cls, dikt) -> 'ProcessedTripVibration':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The ProcessedTrip_vibration of this ProcessedTripVibration.  # noqa: E501
        :rtype: ProcessedTripVibration
        """
        return util.deserialize_model(dikt, cls)

    @property
    def max_vibration(self) -> float:
        """Gets the max_vibration of this ProcessedTripVibration.


        :return: The max_vibration of this ProcessedTripVibration.
        :rtype: float
        """
        return self._max_vibration

    @max_vibration.setter
    def max_vibration(self, max_vibration: float):
        """Sets the max_vibration of this ProcessedTripVibration.


        :param max_vibration: The max_vibration of this ProcessedTripVibration.
        :type max_vibration: float
        """

        self._max_vibration = max_vibration

    @property
    def min_vibration(self) -> float:
        """Gets the min_vibration of this ProcessedTripVibration.


        :return: The min_vibration of this ProcessedTripVibration.
        :rtype: float
        """
        return self._min_vibration

    @min_vibration.setter
    def min_vibration(self, min_vibration: float):
        """Sets the min_vibration of this ProcessedTripVibration.


        :param min_vibration: The min_vibration of this ProcessedTripVibration.
        :type min_vibration: float
        """

        self._min_vibration = min_vibration

    @property
    def avg_vibration(self) -> float:
        """Gets the avg_vibration of this ProcessedTripVibration.


        :return: The avg_vibration of this ProcessedTripVibration.
        :rtype: float
        """
        return self._avg_vibration

    @avg_vibration.setter
    def avg_vibration(self, avg_vibration: float):
        """Sets the avg_vibration of this ProcessedTripVibration.


        :param avg_vibration: The avg_vibration of this ProcessedTripVibration.
        :type avg_vibration: float
        """

        self._avg_vibration = avg_vibration
